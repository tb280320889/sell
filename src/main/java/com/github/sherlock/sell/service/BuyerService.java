package com.github.sherlock.sell.service;

import com.github.sherlock.sell.dto.OrderDTO;

/**
 * Created by TangBin on 2017/8/29.
 */

public interface BuyerService {

  /**
   *
   * @param openid
   * @param orderId
   * @return
   */
  OrderDTO findOrderOne(String openid, String orderId);

  /**
   *
   * @param openid
   * @param orderId
   * @return
   */
  OrderDTO cancelOrder(String openid, String orderId);

}
