package com.github.sherlock.sell.service;

import com.github.sherlock.sell.dto.OrderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by TangBin on 2017/8/28.
 */

public interface OrderService {

  /**
   * create order
   */

  OrderDTO create(OrderDTO orderDTO);

  /**
   * query single order
   */

  OrderDTO findOne(String orderId);

  /**
   * query list of orders
   */

  Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

  /**
   *
   * @param pageable
   * @return
   */
  Page<OrderDTO> findList(Pageable pageable);

  /**
   * cancel order
   */

  OrderDTO cancel(OrderDTO orderDTO);

  /**
   * finish order
   */
  OrderDTO finish(OrderDTO orderDTO);

  /**
   * pay order
   */
  OrderDTO pay(OrderDTO orderDTO);
}
