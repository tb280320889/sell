package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.service.OrderService;
import com.github.sherlock.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by TangBin on 2017/9/5.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PushMessageServiceImplTest {

  @Autowired
  private PushMessageService pushMessageService;

  @Autowired
  private OrderService orderService;


  @Test
  public void orderStatus() throws Exception {
    final OrderDTO orderDTO = orderService.findOne("");
    pushMessageService.orderStatus(orderDTO);

  }


}
