package com.xprice.price_comparator.controller.base;

import com.xprice.price_comparator.model.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class BaseController implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    protected <T> ResponseDto<T> handleError(Exception e){
        Throwable rootCause = e.getCause() != null ? e.getCause() : e;
        log.error("Error: " + e.getMessage() + " | Root cause: " + rootCause.getMessage(), e);

        return new ResponseDto<>("An error occurred while calculating the best price: ", rootCause.getMessage());
    }

}
