package com.github.sherlock.sell.converter;

import com.github.sherlock.sell.domainobject.OrderMaster;
import com.github.sherlock.sell.dto.OrderDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;

/**
 * Created by TangBin on 2017/8/29.
 */

public class OrderMaster2OrderDTOConverter {

  private OrderMaster2OrderDTOConverter() {

  }

  public static OrderDTO convert(OrderMaster orderMaster) {
    OrderDTO orderDTO = new OrderDTO();
    BeanUtils.copyProperties(orderMaster, orderDTO);
    return orderDTO;
  }

  public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
    return orderMasterList.stream().map(OrderMaster2OrderDTOConverter::convert)
        .collect(Collectors.toList());
  }

}
