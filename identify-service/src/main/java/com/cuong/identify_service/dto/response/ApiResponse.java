package com.cuong.identify_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

// những fied nào null sẽ ko xuất hiện object trả về
@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class ApiResponse<T> {
    private int code = 1000;
    private String message;
    private T result;
}
