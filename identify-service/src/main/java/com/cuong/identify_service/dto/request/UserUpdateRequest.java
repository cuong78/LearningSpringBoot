package com.cuong.identify_service.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.cuong.identify_service.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstName;
    String lastName;
    LocalDate dob;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    List<String> roles;
}
