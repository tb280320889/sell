package com.github.sherlock.sell.exception;

import com.github.sherlock.sell.enums.ResultEnum;

/**
 * Created by TangBin on 2017/8/28.
 */

public class SellException extends RuntimeException {

  private Integer code;

  public SellException(ResultEnum resultEnum) {
    super(resultEnum.getMessage());
    this.code = resultEnum.getCode();
  }

  public SellException(Integer code, String message) {
    super(message);
    this.code = code;
  }
}
