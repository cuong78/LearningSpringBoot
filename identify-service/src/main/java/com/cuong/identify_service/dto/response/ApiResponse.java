package com.cuong.identify_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

// những fied nào null sẽ ko xuất hiện object trả về
@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
//generic class
public class ApiResponse<T> {
    @Builder.Default
     int code = 1000;
     String message;
      T result;
}
