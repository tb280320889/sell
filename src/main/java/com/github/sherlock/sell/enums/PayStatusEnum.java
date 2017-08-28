package com.github.sherlock.sell.enums;

import lombok.Getter;

/**
 * Created by TangBin on 2017/8/28.
 */
@Getter
public enum PayStatusEnum {

  WAIT(0, "waiting to be paid"),
  SUCCESS(1, "paid successfully"),;

  private Integer code;
  private String msg;

  PayStatusEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
