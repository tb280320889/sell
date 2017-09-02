package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.service.OrderService;
import com.github.sherlock.sell.service.PayService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/8/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {


  @Autowired
  private PayService payService;

  @Autowired
  private OrderService orderService;


  @Test
  public void create() throws Exception {
    final OrderDTO orderDTO = orderService.findOne("2333333");
    payService.create(orderDTO);

  }

  @Test
  public void refund() throws Exception {
    final OrderDTO orderDTO = orderService.findOne("2333333");
    payService.refund(orderDTO);
  }

}
