package com.github.sherlock.sell.commontest;

import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.enums.OrderStatusEnum;

import org.apache.commons.lang3.EnumUtils;
import org.junit.Test;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/9/1.
 */

@Slf4j
public class EnumUtilTest {


  @Test
  public void testEnumUtil() throws Exception {
    final Map<String, OrderStatusEnum> orderStatusEnumMap = EnumUtils
      .getEnumMap(OrderStatusEnum.class);
    log.info(orderStatusEnumMap.toString());

    final OrderStatusEnum anEnum = EnumUtils.getEnum(OrderStatusEnum.class, "NEW");

    log.info(anEnum.getMsg());

    final OrderDTO orderDTO = new OrderDTO();
    orderDTO.setOrderStatus(1);
    final OrderStatusEnum orderStatusEnum = orderDTO.getOrderStatusEnum();
    log.info(orderStatusEnum.getMsg());

  }
}
