package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.domainobject.OrderDetail;
import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.enums.OrderStatusEnum;
import com.github.sherlock.sell.enums.PayStatusEnum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

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
  private final String ORDER_ID = "1503929894903780965";

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
    orderDetail.setProductId("11");
    orderDetail.setProductQuantity(1);
    orderDetailList.add(orderDetail);

    orderDTO.setOrderDetailList(orderDetailList);

    OrderDTO result = orderService.create(orderDTO);
    log.info("[create order] result = {}", result);
    Assert.assertNotNull(result);
  }

  @Test
  public void findOne() throws Exception {
    OrderDTO result = orderService.findOne(ORDER_ID);
    log.info("[query single order] result = {} ", result);
    Assert.assertEquals(ORDER_ID, result.getOrderId());
  }

  @Test
  public void findList() throws Exception {
    PageRequest pageRequest = new PageRequest(0, 2);
    Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, pageRequest);
    Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
  }

  @Test
  @Transactional
  public void cancel() throws Exception {
    OrderDTO orderD = orderService.findOne(ORDER_ID);
    OrderDTO result = orderService.cancel(orderD);
    Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
  }

  @Test
  @Transactional
  public void finish() throws Exception {
    OrderDTO orderD = orderService.findOne(ORDER_ID);
    OrderDTO result = orderService.finish(orderD);
    Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
  }

  @Test
  @Transactional
  public void pay() throws Exception {
    OrderDTO orderD = orderService.findOne(ORDER_ID);
    OrderDTO result = orderService.pay(orderD);
    Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
  }

  @Test
  public void listAll() throws Exception {
    PageRequest pageRequest = new PageRequest(0, 2);
    Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
    Assert.assertTrue("order number should greater than zero", orderDTOPage.getTotalElements() > 0);
  }

}
