package org.example.todojpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ErrorResponseDto {

    private String code;
    private String message;
    private String status;
    private LocalDateTime timestamp;

    public ErrorResponseDto(String code,String message,String status){
        this.code =code;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }


}
