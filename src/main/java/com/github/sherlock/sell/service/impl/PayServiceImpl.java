package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.service.OrderService;
import com.github.sherlock.sell.service.PayService;
import com.github.sherlock.sell.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.utils.JsonUtil;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/8/31.
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

  private static final String ORDER_NAME = "weChat order";

  private static final String clazzName = PayServiceImpl.class.getName();

  private final BestPayService bestPayService;
  private final OrderService orderService;


  @Lazy
  public PayServiceImpl(BestPayService bestPayService, OrderService orderService) {
    this.bestPayService = bestPayService;
    this.orderService = orderService;
  }

  @Override
  public PayResponse create(OrderDTO orderDTO) {
    final PayRequest payRequest = new PayRequest();
    payRequest.setOpenid(orderDTO.getBuyerOpenid());
    payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
    payRequest.setOrderId(orderDTO.getOrderId());
    payRequest.setOrderName(ORDER_NAME);
    payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
    final PayResponse payResponse = bestPayService.pay(payRequest);

    log.info("#weChat pay# request={}", JsonUtil.toJson(payRequest));
    log.info("#weChat pay# response={}", JsonUtil.toJson(payResponse));
    return payResponse;
  }

  @Override
  public PayResponse notify(String notifyData) {

    //FIXME need attention!
    // verify signature

    final PayResponse payResponse = bestPayService.asyncNotify(notifyData);
    // modify pay status
    final String orderId = payResponse.getOrderId();
    final OrderDTO orderDTO = orderService.findOne(orderId);
    if (orderDTO == null) {
      log.error("#weChat pay# asynchronous notification, orderId={}", orderId);
      throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }

    // verify price amount
    // pay user (order user == pay user)
    final BigDecimal orderAmountFromSystem = orderDTO.getOrderAmount();
    final Double orderAmountFromWeChat = payResponse.getOrderAmount();
    if (!MathUtil
      .priceEquals(orderAmountFromSystem, orderAmountFromWeChat)) {
      log.error(
        "#weChat pay# asynchronous notification , orderId={}, price from notification={}, price from system={} ",
        orderId, orderAmountFromWeChat, orderAmountFromSystem.doubleValue());
      throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
    }

    // pay status
    orderService.pay(orderDTO);

    return payResponse;
  }

  @Override
  public RefundRequest refund(OrderDTO orderDTO) {
    final RefundRequest refundRequest = new RefundRequest();
    refundRequest.setOrderId(orderDTO.getOrderId());
    refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
    refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
    log.info("#weChat refund# request={} ", JsonUtil.toJson(refundRequest));

    final RefundResponse refundResponse = bestPayService.refund(refundRequest);

    log.info("#weChat refund# response={}", JsonUtil.toJson(refundResponse));
    return refundRequest;
  }
}
