package com.kk.code.mall.exception;

import com.kk.code.mall.common.api.DataResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: hyspgxptkf
 * @Package: com.vdata.cloud.hyspgxptkf.exception
 * @ClassName: GlobalExceptionHandler
 * @Author: HK
 * @Description:
 * @Date: 2023/4/4 10:40
 * @Version: 1.0
 */
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public DataResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("出现了错误", e);
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            sb.append(error.getDefaultMessage()).append(";");
        }
        return DataResult.error(sb.toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DataResult handleException(Exception e) {
        // 处理异常并返回结果
        log.error("出现了错误", e);
        return DataResult.error(e.getMessage());
    }

}

