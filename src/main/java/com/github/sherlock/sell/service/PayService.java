package com.github.sherlock.sell.service;

import com.github.sherlock.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;

/**
 * Created by TangBin on 2017/8/31.
 */

public interface PayService {

  /**
   *
   * @param orderDTO
   */
  PayResponse create(OrderDTO orderDTO);

  /**
   *
   * @param notifyData
   * @return
   */
  PayResponse notify(String notifyData);

  /**
   *
   * @param orderDTO
   */
  RefundRequest refund(OrderDTO orderDTO);
}
