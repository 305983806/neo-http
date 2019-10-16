package com.neo.http.sample.hello.server.common;

import com.alibaba.fastjson.JSONObject;
import com.neo.http.common.NeoHttpException;
import com.neo.http.common.bean.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-16 14:20
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NeoHttpException.class})
    public ResponseEntity handleNeoHttpException(NeoHttpException e) {
        Error error = e.getError();
        return new ResponseEntity(error.getMessage(), HttpStatus.OK);
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
