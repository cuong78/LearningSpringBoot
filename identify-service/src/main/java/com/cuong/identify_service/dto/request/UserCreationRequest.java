package com.cuong.identify_service.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.cuong.identify_service.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // inject vào contructer thông qua Buider
@FieldDefaults(level = AccessLevel.PRIVATE) // mọi field là private
public class UserCreationRequest {

    @NotBlank // spring nó sẽ dự vào message sẽ tự đi tìm Errorcode tương ứng
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    String firstName;
    String lastName;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;
}
