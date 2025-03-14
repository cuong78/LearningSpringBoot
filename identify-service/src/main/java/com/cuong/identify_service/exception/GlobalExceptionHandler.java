package com.cuong.identify_service.exception;

import com.cuong.identify_service.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
//khai báo class này , khi có exception , thì class này sẽ đảm nhiệm
public class GlobalExceptionHandler {

    // bất cứ 1 exception runtimeException ở đâu xayr ra trong hệ thống
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();

        // mặc định là enumkey
        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try{
            // kiểm tra xem enumkey có trùng với Errocode đã khai báo không
            // nếu có thì gán errcode,
            // nếu không thì giữ mặc định errorCode là INVALID_KEY
          errorCode = ErrorCode.valueOf(enumKey);

      }catch (IllegalArgumentException e){

      }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
        // fieldError
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }



}
