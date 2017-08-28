package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.domainobject.OrderDetail;
import com.github.sherlock.sell.dto.OrderDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by TangBin on 2017/8/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

  @Autowired
  private OrderServiceImpl orderService;

  private final String BUYER_OPENID = "110233";

  @Test
  public void create() throws Exception {
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setBuyerName("sherlock");
    orderDTO.setBuyerAddress("China");
    orderDTO.setBuyerPhone("1224343422");
    orderDTO.setBuyerOpenid(BUYER_OPENID);

    //cart
    List<OrderDetail> orderDetailList = new ArrayList<>();
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setProductId("123");
    orderDetail.setProductQuantity(1);
    orderDetailList.add(orderDetail);

    orderDTO.setOrderDetailList(orderDetailList);

    OrderDTO result = orderService.create(orderDTO);
    log.info("[create order] result = {}", result);
    Assert.assertNotNull(result);
  }

  @Test
  public void findOne() throws Exception {
  }

  @Test
  public void findList() throws Exception {
  }

  @Test
  public void cancel() throws Exception {
  }

  @Test
  public void finish() throws Exception {
  }

  @Test
  public void pay() throws Exception {
  }

}
