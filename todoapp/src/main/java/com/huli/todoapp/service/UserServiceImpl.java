package com.huli.todoapp.service;

import com.huli.todoapp.model.DTO.LoginDto;
import com.huli.todoapp.model.DTO.UserDto;
import com.huli.todoapp.model.entity.User;
import com.huli.todoapp.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDto userRegistration(UserDto userDto) {
        if (userRepository.findUserByUsername(userDto.getUsername())!=null){
            throw new IllegalArgumentException("Username already exists!");
        }
        if (userRepository.findUserByEmail(userDto.getEmail())!=null){
            throw new IllegalArgumentException("Email already exists!");
        }
        User user = userRepository.save(new User(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getEmail(),
                "user"));
        return new UserDto(user.getId(),user.getUsername(),"protected",user.getEmail());
    }

    @Override
    public String createToken(LoginDto loginDto) {
        if (loginDto.getUsername()==null) throw new IllegalArgumentException("Username is missing");
        if (loginDto.getPassword()==null) throw new IllegalArgumentException("Password is missing");
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        return generateToken(authentication);
    }

    private String generateToken(Authentication authentication){
        String jwt="";
        if (authentication!=null){
            SecretKey key = Keys.hmacShaKeyFor(System.getenv("JWT_KEY").getBytes(StandardCharsets.UTF_8));
            jwt = Jwts.builder().setIssuer("TodoApp").setSubject("JWT Token")
                    .claim("username",authentication.getName())
                    .claim("id",userRepository.findUserByUsername(authentication.getName()).getId())
                    .claim("authorities",getAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime()+30000000))
                    .signWith(key).compact();
        }
        return jwt;
    }

    private String getAuthorities (Collection<? extends GrantedAuthority> authorities){
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority:authorities){
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",",authoritiesSet);
    }
}
