package com.triply.barrierfreetrip;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class JwtExceptionResponse {
    private String exceptionMessage;
    private HttpStatus status;
    private String code;

    public String convertToJson(JwtExceptionResponse jwtExceptionResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(jwtExceptionResponse);

        return json;
    }
}
