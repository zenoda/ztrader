package org.zenoda.ztrader.admin.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 1. 业务异常（自定义 BusinessException）
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(
            BusinessException ex, HttpServletRequest request) {
        logger.warn("业务异常: {}", ex.getMessage());
        // 业务异常默认返回 400，也可从异常中获取自定义状态码
        HttpStatus status = ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.BAD_REQUEST;
        ErrorResponse error = new ErrorResponse(status, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    // 2. HTTP 4xx 异常（Spring 抛出的 ResponseStatusException，包含 4xx 状态码）
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(
            ResponseStatusException ex, HttpServletRequest request) {
        logger.warn("HTTP 4xx 异常: {}", ex.getReason());
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value());
        String message;
        if (ex.getReason() != null) {
            message = ex.getReason();
        } else {
            assert status != null;
            message = status.getReasonPhrase();
        }
        assert status != null;
        ErrorResponse error = new ErrorResponse(status, message, request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    // 3. 其他所有异常（500 内部错误）
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllOtherExceptions(
            Exception ex, HttpServletRequest request) {
        logger.error("服务器内部错误", ex);
        String message = "服务器内部错误，请稍后重试";
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


}
