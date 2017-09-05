package com.github.sherlock.sell.service;

import com.github.sherlock.sell.dto.OrderDTO;

/**
 * Created by TangBin on 2017/9/5.
 */

public interface PushMessageService {
  void orderStatus(OrderDTO orderDTO);
}
