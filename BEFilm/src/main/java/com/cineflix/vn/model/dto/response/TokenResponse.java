package com.cineflix.vn.model.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponse {
    String access_token;
    String refresh_token;
}
