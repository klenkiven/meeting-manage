package org.tyut4113.meeting.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tyut4113.meeting.common.utils.Result;

/**
 * 实验室统一业务异常
 *
 * @author klenkiven
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> error(Exception e) {
        e.printStackTrace();
        return Result.fail().message(e.getMessage());
    }

    @ExceptionHandler(GeneralException.class)
    @ResponseBody
    public Result<?> labException(GeneralException e) {
        e.printStackTrace();
        return Result.fail().message(e.getMessage());
    }
}
