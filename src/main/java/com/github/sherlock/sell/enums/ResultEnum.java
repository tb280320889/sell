package com.github.sherlock.sell.enums;

import lombok.Getter;

/**
 * Created by TangBin on 2017/8/28.
 */

@Getter
public enum ResultEnum {
  PARA_ERROR(1, "para is not correct "),
  PRODUCT_NOT_EXIST(10, "product not exists!"),
  PRODUCT_STOCK_ERROR(11, "invalid stock request!"),
  ORDER_NOT_EXIST(12, "order not exists!"),
  ORDER_DETAIL_NOT_EXIST(13, "order detail not exists!"),
  ORDER_STATUS_ERROR(14, "order status error"),
  ORDER_UPDATE_FAIL(15, "order update failed"),
  ORDER_DETAIL_EMPTY(16, "order detail empty"),
  ORDER_PAY_STATUS_ERROR(17, "order pay status error"),
  CART_EMPTY(18, "cart is empty!"),
  ORDER_OWNER_ERROR(19, "openid matches failed"),
  ;
  private Integer code;
  private String message;

  ResultEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
