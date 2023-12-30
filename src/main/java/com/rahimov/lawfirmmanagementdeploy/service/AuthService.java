package com.rahimov.lawfirmmanagementdeploy.service;


import com.rahimov.lawfirmmanagementdeploy.dto.request.GrantRequest;
import com.rahimov.lawfirmmanagementdeploy.dto.request.RefreshTokenRequest;
import com.rahimov.lawfirmmanagementdeploy.dto.request.SignInRequest;
import com.rahimov.lawfirmmanagementdeploy.dto.request.SignUpRequest;
import com.rahimov.lawfirmmanagementdeploy.dto.response.JwtAuthResponse;
import com.rahimov.lawfirmmanagementdeploy.enums.Role;
import com.rahimov.lawfirmmanagementdeploy.model.User;
import com.rahimov.lawfirmmanagementdeploy.repository.UserRepository;
import com.rahimov.lawfirmmanagementdeploy.service.Impl.JWTServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTServiceImpl jwtServiceImpl;

    public User signUpReq(SignUpRequest signUpRequest) {
        User user = new User();

        if (userRepository.findByUsername(signUpRequest.getEmail()) == null) {
            user.setFirstName(signUpRequest.getFirstName());
            user.setSecondName(signUpRequest.getLastName());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setUsername(signUpRequest.getUsername());
            user.setEmail(signUpRequest.getEmail());
            user.setRole(Role.USER);
//            user.setImage(image);


            userRepository.save(user);

        }
        return user;
    }

    public JwtAuthResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                        signInRequest.getPassword()));
        System.out.println(signInRequest);
        User user = userRepository.findByUsername(signInRequest.getUsername()   );
        System.out.println(user);
        String jwt = jwtServiceImpl.generateToken(user);

        String refreshToken = jwtServiceImpl.generateRefreshToken(new HashMap<>(), user);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();


        jwtAuthResponse.setToken(jwt);
        jwtAuthResponse.setRefreshToken(refreshToken);

        return jwtAuthResponse;
    }

    public JwtAuthResponse refreshToken(RefreshTokenRequest refresh) {

        String token = refresh.getToken();
        String username = jwtServiceImpl.extractUsername(token);
        User user = userRepository.findByUsername(username);


        if (jwtServiceImpl.isTokenValid(token, user)) {
            String newToken = jwtServiceImpl.generateToken(user);

            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

            jwtAuthResponse.setToken(newToken);
            jwtAuthResponse.setRefreshToken(refresh.getToken());


            return jwtAuthResponse;
        } else return null;

    }

    public JwtAuthResponse grant(GrantRequest grantRequest) {
        String token = grantRequest.getToken();
        String username = jwtServiceImpl.extractUsername(token);
        User user = userRepository.findByUsername(username);


        user.setRole(Role.ADMIN);

        userRepository.save(user);

        return JwtAuthResponse.builder()
                .token(jwtServiceImpl.generateToken(user))
                .build();
    }
}