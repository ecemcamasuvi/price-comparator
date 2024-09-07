package com.xprice.price_comparator.controller;

import com.xprice.price_comparator.controller.base.BaseController;
import com.xprice.price_comparator.model.dto.ProductPriceDto;
import com.xprice.price_comparator.model.dto.ResponseDto;
import com.xprice.price_comparator.service.PriceComparatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "app/price-comparator")
public class PriceComparatorController extends BaseController {

    @Autowired
    private PriceComparatorService priceComparatorService;

    @ResponseBody
    @RequestMapping(value = "/best-price", method = RequestMethod.GET)
    public ResponseDto<ProductPriceDto> getBestPrice() {
        try {
            ProductPriceDto bestPrice = priceComparatorService.getBestPrice();
            return new ResponseDto<>(bestPrice);
        } catch (Exception e) {
            return handleError(e);
        }
    }

}
