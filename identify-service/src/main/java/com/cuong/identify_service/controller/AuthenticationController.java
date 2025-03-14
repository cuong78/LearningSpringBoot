package com.cuong.identify_service.controller;

import com.cuong.identify_service.dto.request.AuthenticationRequest;
import com.cuong.identify_service.dto.response.ApiResponse;
import com.cuong.identify_service.dto.response.AuthenticationResponse;
import com.cuong.identify_service.service.AuthenticationService;
import com.cuong.identify_service.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping ("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    // là một kiểu dữ liệu được trả về
    ApiResponse<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {
        boolean result =  authenticationService.authenticate(authenticationRequest);

        return  // ApiResponse<AuthenticationResponse> là một kiểu dữ liệu generic,
                // trong đó T được chỉ định là AuthenticationResponse.
                // builder() là phương thức tĩnh được Lombok sinh ra,
                // giúp khởi tạo một đối tượng ApiResponse với kiểu dữ liệu T là AuthenticationResponse.
                // Vì ApiResponse là một lớp generic (ApiResponse<T>),
                // nên khi dùng builder(), phải chỉ định kiểu dữ liệu cho T.
                // Ở đây, T là AuthenticationResponse.
                ApiResponse.<AuthenticationResponse>builder()
                        // Trong ApiResponse có một trường tên là result,
                        // và trường này có kiểu dữ liệu là T (AuthenticationResponse).
                        // Do đó, cần truyền vào một đối tượng AuthenticationResponse
                        // để gán cho trường result.
                        .result(
                                // AuthenticationResponse.builder() khởi tạo một builder
                                // để xây dựng đối tượng AuthenticationResponse.
                                AuthenticationResponse.builder()
                                        // Trong AuthenticationResponse có một trường tên là authenticated,
                                        //  thiết lập giá trị cho trường này bằng biến result (true/false).
                                        .authenticated(result)
                                        // .build() kết thúc quá trình xây dựng
                                        // và trả về một đối tượng AuthenticationResponse hoàn chỉnh.
                                        .build()
                        )
                        // .build() kết thúc quá trình xây dựng ApiResponse
                        // và trả về một đối tượng ApiResponse hoàn chỉnh.
                        .build();

    }


}
