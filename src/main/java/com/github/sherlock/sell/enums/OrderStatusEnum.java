package com.github.sherlock.sell.enums;

import lombok.Getter;

/**
 * Created by TangBin on 2017/8/28.
 */
@Getter
public enum OrderStatusEnum implements EnumMessage {
  NEW(0, "new order"),
  FINISHED(1, "order finished"),
  CANCEL(2, "have been canceled"),;

  private Integer code;
  private String msg;

  OrderStatusEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
