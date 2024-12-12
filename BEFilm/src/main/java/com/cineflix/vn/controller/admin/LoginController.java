package com.cineflix.vn.controller.admin;

//import com.cineflix.vn.service.CustomerUserDetail;
import com.cineflix.vn.model.User;
import com.cineflix.vn.model.dto.request.UserRequest;
import com.cineflix.vn.model.dto.response.TokenResponse;
import com.cineflix.vn.model.dto.response.UserResponse;
import com.cineflix.vn.model.mapper.UserMapper;
import com.cineflix.vn.service.CustomerUserDetail;
import com.cineflix.vn.service.RedisService;
import com.cineflix.vn.service.modelService.service.UserService;
import com.cineflix.vn.util.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//TODO: config return controller using a APIResponse


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    @Lazy
    CustomerUserDetail customerUserDetail;
    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;
    @Autowired
    @Lazy
    RedisService redisService;
    @Autowired
    @Lazy
    JWTUtil jwtUtil;

    @GetMapping("/get")
    public String getToken(@RequestParam String username) {
        String tokenKey = username;
        return redisService.getToken(tokenKey);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        try {
            UserDetails user = customerUserDetail.loadUserByUsername(username);
            UserResponse userResponse ;
            if (passwordEncoder.matches(password, user.getPassword())) {
                String accessToken = jwtUtil.generateAccesstoken(user);
                String refreshToken = jwtUtil.generateRefreshToken(user);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshToken", refreshToken);
                List<String> roles = jwtUtil.extractRoles(accessToken);
                for (String role : roles) {
                    System.out.println("Role: " + role);
                }
                redisService.saveTokenAsync(user.getUsername(), accessToken);
                Cookie cookie = new Cookie("auth_token", accessToken);
                cookie.setHttpOnly(true);
                cookie.setSecure(false);
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24);
                response.addHeader("Set-Cookie", "auth_token=" + accessToken + "; HttpOnly; Secure; Path=/; Max-Age=86400; SameSite=Strict");
                response.addCookie(cookie);
                Cookie coo = new Cookie("auth_token", jwtUtil.generateAccesstoken(user));
                return ResponseEntity.ok(coo);
            } else {
                return ResponseEntity.badRequest().body("Invalid username or password");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }


//    @CrossOrigin(origins = "*")
//    @GetMapping("/login/google")
//    public ResponseEntity<String> loginWithGoogle() {
//        ClientRegistration googleClientRegistration = clientRegistrationRepository.findByRegistrationId("google");
//        if (googleClientRegistration == null) {
//            return ResponseEntity.badRequest().body("Google client registration not found.");
//        }
//        String authorizationUri = googleClientRegistration.getProviderDetails().getAuthorizationUri();
//        String clientId = googleClientRegistration.getClientId();
//        String redirectUri = googleClientRegistration.getRedirectUri();
//        String scope = String.join(" ", googleClientRegistration.getScopes());
//        String loginUrl = authorizationUri
//                + "?response_type=code"
//                + "&client_id=" + clientId
//                + "&redirect_uri=" + redirectUri
//                + "&scope=" + scope;
//
//        return ResponseEntity.ok(loginUrl);
//    }

// FUTURE: update when remember me
//    public UserResponse UpdateAllToken(String newAccToken,String newRefToken, UserDetails userDetails){
//        User newUser = userService.findByUsername(userDetails.getUsername());
//        newUser.setAccessToken(newAccToken);
//        newUser.setRefreshToken(newRefToken);
//        UserRequest us = UserMapper.INSTANCE.TranferToUserRequestfromUser(newUser);
//        return userService.updateUser(us);
//    }
//
//    public UserResponse UpdateAccessTokenForUser(String newToken, UserDetails userDetails){
//        User newUser = userService.findByUsername(userDetails.getUsername());
//        newUser.setAccessToken(newToken);
//        UserRequest us = UserMapper.INSTANCE.TranferToUserRequestfromUser(newUser);
//        return userService.updateUser(us);
//    }
//
//    public UserResponse AddTokenUser(UserDetails user) {
//        User newUser = userService.findByUsername(user.getUsername());
//        newUser.setAccessToken(jwtUtil.generateAccesstoken(user));
//        newUser.setRefreshToken(jwtUtil.generateRefreshToken(user));
//        UserRequest userRequest = UserMapper.INSTANCE.TranferToUserRequestfromUser(newUser);
//        System.out.println(userRequest.getUsername());
//        return userService.updateUser(userRequest);
//    }
//
//    public Boolean CheckTokenExpired(String token) {
//        return jwtUtil.isTokenExpired(token);
//    }
//
//    public TokenResponse getTokenInUser(String username){
//        User us = userService.findByUsername(username);
//        TokenResponse tokenResponse = new TokenResponse();
//        tokenResponse.setAccess_token(us.getAccessToken());
//        tokenResponse.setRefresh_token(us.getRefreshToken());
//        return tokenResponse;
//    }
//
//
//    public Cookie generateTokenAndSaveToResdis(UserDetails userDetails,HttpServletResponse response) {
//        String accessToken = jwtUtil.generateAccesstoken(userDetails);
//        String refreshToken = jwtUtil.generateRefreshToken(userDetails);
//        Map<String, String> tokens = new HashMap<>();
//        tokens.put("accessToken", accessToken);
//        tokens.put("refreshToken", refreshToken);
//        List<String> roles = jwtUtil.extractRoles(accessToken);
//        for (String role : roles) {
//            System.out.println("Role: " + role);
//        }
////        redisService.saveToken(username, accessToken);
//        redisService.saveTokenAsync(userDetails.getUsername(), accessToken);
//        Cookie cookie = new Cookie("auth_token", accessToken);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(false);
//        cookie.setPath("/");
//        cookie.setMaxAge(60 * 60 * 24);
//        response.addHeader("Set-Cookie", "auth_token=" + accessToken + "; HttpOnly; Secure; Path=/; Max-Age=86400; SameSite=Strict");
//        response.addCookie(cookie);
//        Cookie coo = new Cookie("auth_token", jwtUtil.generateAccesstoken(userDetails));
//        return coo;
//    }
}
