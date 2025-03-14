package com.cuong.identify_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  // inject vào contructer thông qua Buider
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
     String username;
     String password;
}
