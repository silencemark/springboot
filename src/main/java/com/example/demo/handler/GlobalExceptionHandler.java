package com.example.demo.handler;

import com.example.demo.entity.JsonResult;
import com.example.demo.exception.BusinessErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    // 打印log
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // ……

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonResult handleHttpMessageNotReadableException(
            MissingServletRequestParameterException ex) {
        logger.error("缺少请求参数，{}", ex.getMessage());
        return new JsonResult("400", "缺少必要的请求参数");
    }

    /**
     * 空指针异常
     * @param ex NullPointerException
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleTypeMismatchException(NullPointerException ex) {
        logger.error("空指针异常，{}", ex.getMessage());
        return new JsonResult("500", "空指针异常了");
    }

    /*在实际项目中，除了拦截一些系统异常外，在某些业务上，我们需要自定义一些业务异常，
    比如在微服务中，服务之间的相互调用很平凡，很常见。要处理一个服务的调用时，
    那么可能会调用失败或者调用超时等等，此时我们需要自定义一个异常，当调用失败时
    抛出该异常，给 GlobalExceptionHandler 去捕获*/

    /**
     * 拦截业务异常，返回业务异常信息
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleBusinessError(BusinessErrorException ex) {
        String code = ex.getCode();
        String message = ex.getMessage();
        return new JsonResult(code, message);
    }

    /**
     * 系统异常 预期以外异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleUnexpectedServer(Exception ex) {
        logger.error("系统异常：", ex);
        return new JsonResult("500", "系统发生异常，请联系管理员");
    }

    /*项目中，我们一般都会比较详细的去拦截一些常见异常，拦截 Exception 虽然可以
    一劳永逸，但是不利于我们去排查或者定位问题。实际项目中，可以把拦截
    Exception 异常写在 GlobalExceptionHandler 最下面，
    如果都没有找到，最后再拦截一下 Exception 异常，保证输出信息友好。*/
}