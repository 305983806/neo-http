package com.neo.http.server;

import com.alibaba.fastjson.JSONObject;
import com.neo.http.common.lang.NeoHttpException;
import com.neo.http.common.bean.Error;
import com.neo.http.common.bean.SystemError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-16 14:20
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler({NeoHttpException.class})
    public ResponseEntity handleNeoHttpException(NeoHttpException e) {
        Error error = e.getError();
        return new ResponseEntity(getJSONError(error), HttpStatus.OK);
    }

    /**
     *  缺少参数
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> params = new LinkedHashMap<>();
        Error error = SystemError.SYS_MISSING_PARAMETER;
        String json = getJSONError(error, ex.getParameterName());
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    /**
     * 实体参数校验不通过
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = SystemError.SYS_INVALID_PARAMETER;
        return new ResponseEntity<>(getJSONError(error), HttpStatus.BAD_REQUEST);
    }

    /**
     * 方法参数校验不通过
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Error error = SystemError.SYS_INVALID_PARAMETER;
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String s = null;
        for (ConstraintViolation<?> item : violations) {
            s = item.getInvalidValue() + "(" + item.getMessage() + ")";
        }
        return new ResponseEntity<>(getJSONError(error, s), HttpStatus.BAD_REQUEST);
    }

    /**
     * 非法的请求方式
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = SystemError.SYS_UNSUPPORTED_HTTP_METHOD;
        return new ResponseEntity<>(getJSONError(error), HttpStatus.BAD_REQUEST);
    }

    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleRuntimeException(RuntimeException e) {
        logger.error(e.getMessage(), e);
        Error error = SystemError.SYS_BUSY;
        return new ResponseEntity(getJSONError(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(e.getMessage(), e);
        Error error = SystemError.SYS_BUSY;
        return new ResponseEntity(getJSONError(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getJSONError(Error error) {
        return getJSONError(error,"");
    }

    private String getJSONError(Error error, String msg) {
        Map<String, Object> errMap = new LinkedHashMap<>();
        errMap.put("code", error.getCode());

        if(StringUtils.isEmpty(msg)){
            errMap.put("message", error.getMessage() + msg);
        }else {
            errMap.put("message", error.getMessage() + " : " + msg);
        }

        return JSONObject.toJSONString(errMap);
    }

}
