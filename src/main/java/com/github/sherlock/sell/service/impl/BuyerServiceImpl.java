package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.service.BuyerService;
import com.github.sherlock.sell.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/8/29.
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

  private final OrderService orderService;

  @Autowired
  public BuyerServiceImpl(OrderService orderService) {
    this.orderService = orderService;
  }


  @Override
  public OrderDTO findOrderOne(String openid, String orderId) {
    final OrderDTO orderDTO = orderService.findOne(openid);
    if (orderDTO == null) {
      return null;
    }
    //judge whether order openid matches
    if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
      log.error("#query order# openid inconsistent openid = {} ,orderDTO = {} ", openid, orderDTO);
      throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
    }
    return orderDTO;
  }


  @Override
  public OrderDTO cancelOrder(String openid, String orderId) {
    final OrderDTO orderDTO = findOrderOne(openid, orderId);
    if (orderDTO == null) {
      log.error("#cancel order# order not exists , orderId = {}", orderId);
      throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }
    return orderService.cancel(orderDTO);
  }


}
