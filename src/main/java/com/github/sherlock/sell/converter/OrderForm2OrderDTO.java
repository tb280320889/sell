package com.github.sherlock.sell.converter;

import com.github.sherlock.sell.domainobject.OrderDetail;
import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/8/29.
 */
@Slf4j
public class OrderForm2OrderDTO {

  private OrderForm2OrderDTO() {
  }

  public static OrderDTO convert(OrderForm orderForm) {

    final OrderDTO orderDTO = new OrderDTO();
    orderDTO.setBuyerName(orderForm.getName());
    orderDTO.setBuyerPhone(orderForm.getPhone());
    orderDTO.setBuyerAddress(orderForm.getAddress());
    orderDTO.setBuyerOpenid(orderForm.getOpenid());

    final String orderFormItems = orderForm.getItems();

    List<OrderDetail> orderDetailList;
    try {
      final Gson gson = new Gson();
      orderDetailList = gson.fromJson(orderFormItems, new TypeToken<List<OrderDetail>>() {
      }.getType());
    } catch (Exception e) {
      log.error("#object convert# error, json = {} ", orderFormItems);
      throw new SellException(ResultEnum.PARA_ERROR);
    }
    orderDTO.setOrderDetailList(orderDetailList);
    return orderDTO;
  }
}
