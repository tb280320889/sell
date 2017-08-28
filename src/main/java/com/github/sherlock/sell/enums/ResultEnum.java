package com.github.sherlock.sell.enums;

import lombok.Getter;

/**
 * Created by TangBin on 2017/8/28.
 */

@Getter
public enum ResultEnum {
  PRODUCT_NOT_EXIST(10, "product not exists!"),
  PRODUCT_STOCK_ERROR(11,"invalid stock request!")
  ;
  private Integer code;
  private String message;

  ResultEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
