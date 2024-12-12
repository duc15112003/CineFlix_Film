//package com.cineflix.vn.service;
//
//import com.cineflix.vn.model.dto.request.CustomOAuth2User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//
//    private final UserDetailsService userDetailsService;
//
//    public CustomOAuth2UserService(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
//        OAuth2User oAuth2User = new DefaultOAuth2User(
//                userRequest.getClientRegistration().getScopes(),
//                userRequest.getAdditionalParameters(),
//                "email"); // Assuming "email" is the unique identifier in OAuth2 response
//
//        // Here you can use the oAuth2User to create a custom UserDetails object
//        return new CustomOAuth2User(oAuth2User);
//    }
//}
