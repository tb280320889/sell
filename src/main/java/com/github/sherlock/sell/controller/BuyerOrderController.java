package com.github.sherlock.sell.controller;

import com.github.sherlock.sell.converter.OrderForm2OrderDTO;
import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.form.OrderForm;
import com.github.sherlock.sell.service.BuyerService;
import com.github.sherlock.sell.service.OrderService;
import com.github.sherlock.sell.utils.ResultVOUtil;
import com.github.sherlock.sell.vo.ResultVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/8/29.
 */

@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {

  private final OrderService orderService;
  private final BuyerService buyerService;

  @Autowired
  public BuyerOrderController(OrderService orderService,
                              BuyerService buyerService) {
    this.orderService = orderService;
    this.buyerService = buyerService;
  }

  /**
   * create order
   */
  @PostMapping("create")
  public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                              BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      log.error("#create order # para is not correct , orderForm = {} ", orderForm);
      throw new SellException(ResultEnum.PARA_ERROR.getCode(),
        bindingResult.getFieldError().getDefaultMessage());
    }

    final OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
    if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
      log.error("#create order# cart is empty");
      throw new SellException(ResultEnum.CART_EMPTY);
    }

    final OrderDTO createResult = orderService.create(orderDTO);
    final HashMap<String, String> map = new HashMap<>();
    map.put("orderId", createResult.getOrderId());

    return ResultVOUtil.success(map);
  }

  /**
   * show list of order
   */
  @GetMapping("list")
  public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
    if (StringUtils.isEmpty(openid)) {
      log.error("#query order list# openid is null");
      throw new SellException(ResultEnum.PARA_ERROR);
    }

    final PageRequest pageRequest = new PageRequest(page, size);
    final Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
    return ResultVOUtil.success(orderDTOPage.getContent());

  }

  /**
   * show order detail
   */
  @GetMapping("detail")
  public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                   @RequestParam("orderId") String orderId) {

    final OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
    return ResultVOUtil.success(orderDTO);
  }

  /**
   * cancel order
   */
  @PostMapping("cancel")
  public ResultVO cancel(@RequestParam("openid") String openid,
                         @RequestParam("orderId") String orderId) {

    buyerService.cancelOrder(openid, orderId);
    return ResultVOUtil.success();
  }
}
