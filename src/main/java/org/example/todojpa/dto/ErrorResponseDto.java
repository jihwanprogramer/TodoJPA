package org.example.todojpa.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {

    /**
     * 오류 코드.
     */
    private String code;

    /**
     * 오류 메시지.
     */
    private String message;

    /**
     * HTTP 상태.
     */
    private String status;

    /**
     * 타임스탬프.
     */
    private LocalDateTime timestamp;

    /**
     * 오류 응답 DTO의 생성자입니다.
     *
     * @param code 오류 코드
     * @param message 오류 메시지
     * @param status HTTP 상태
     */
    public ErrorResponseDto(String code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
