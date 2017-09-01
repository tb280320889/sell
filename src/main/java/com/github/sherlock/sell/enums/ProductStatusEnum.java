package com.github.sherlock.sell.enums;

import lombok.Getter;

/**
 * Created by TangBin on 2017/8/27.
 */

@Getter //FIXME need attention!
public enum ProductStatusEnum implements EnumMessage {

  UP(0, "on shelf"),
  DOWN(1, "off shelf");

  private Integer code;
  private String msg;

  ProductStatusEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
