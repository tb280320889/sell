package com.github.sherlock.sell.controller;

import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.service.OrderService;
import com.github.sherlock.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/8/31.
 */


@Controller //FIXME need attention!
@RequestMapping("pay")
@Slf4j
public class PayController {

  private final OrderService orderService;
  private final PayService payService;

  @Autowired
  public PayController(OrderService orderService, PayService payService) {
    this.orderService = orderService;
    this.payService = payService;
  }

  /***
   *
   * @param orderId
   * @param returnUrl
   * @param map
   * @return
   */
  @GetMapping("create")
  public ModelAndView create(@RequestParam("orderId") String orderId,
                             @RequestParam("returnUrl") String returnUrl, Map<String, Object> map) {

    //query order
    final OrderDTO orderDTO = orderService.findOne(orderId);
    if (orderDTO == null) {
      throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }

    final PayResponse payResponse = payService.create(orderDTO);

    map.put("payResponse", payResponse);
    map.put("returnUrl", returnUrl);

    //start pay
    return new ModelAndView("pay/create", map); //FIXME need attention!
  }

  /**
   *
   * @param notifyData
   */
  @PostMapping("notify")
  public ModelAndView notify(@RequestBody String notifyData) {

    payService.notify(notifyData);

    //FIXME need attention!
    // tell weChat to stop sending notification for this order
    return new ModelAndView("pay/success");
  }


}
