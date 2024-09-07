package com.xprice.price_comparator.controller.base;

import com.xprice.price_comparator.model.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class BaseController implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    protected <T> ResponseDto<T> handleError(Exception e){
        log.error(e.getMessage());
        return new ResponseDto<>("An error occured while calculating the best price: ", e.getMessage());
    }

}
