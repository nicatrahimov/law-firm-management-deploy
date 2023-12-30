package com.rahimov.lawfirmmanagementdeploy.controller;

import com.rahimov.lawfirmmanagementdeploy.dto.request.GrantRequest;
import com.rahimov.lawfirmmanagementdeploy.dto.request.RefreshTokenRequest;
import com.rahimov.lawfirmmanagementdeploy.dto.request.SignInRequest;
import com.rahimov.lawfirmmanagementdeploy.dto.request.SignUpRequest;
import com.rahimov.lawfirmmanagementdeploy.dto.response.JwtAuthResponse;
import com.rahimov.lawfirmmanagementdeploy.model.User;
import com.rahimov.lawfirmmanagementdeploy.service.AuthService;
import com.rahimov.lawfirmmanagementdeploy.service.ImageService;
import com.rahimov.lawfirmmanagementdeploy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthService authService;
    private final ImageService imageService;
    private final UserService userService;




    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest sign) throws IOException {
        User user = new User();
        System.out.println(sign);
        if (userService.getByUsername(sign.getUsername())==null &&
            userService.getByEmail(sign.getEmail())==null){
            user = authService.signUpReq(sign);
        }
//        if (user.getId() != null && !photo.isEmpty()) {
//            imageService.uploadImage(photo, user.getId());
//        }else throw new UsernameNotFoundException("User id is null!");

        return new ResponseEntity<>("good",
                HttpStatusCode.valueOf(200));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignInRequest sign){
        return new ResponseEntity<>(authService.signIn(sign),
                HttpStatusCode.valueOf(200));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthResponse>refresh(@RequestBody RefreshTokenRequest refresh){
        return new ResponseEntity<>(authService.refreshToken(refresh)
                ,HttpStatusCode.valueOf(200));

    }
    @PostMapping("/grant")
    public JwtAuthResponse grantUser(@RequestBody GrantRequest grantRequest){
       return authService.grant(grantRequest);

    }

}