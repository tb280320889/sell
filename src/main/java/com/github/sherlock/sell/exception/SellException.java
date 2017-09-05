package com.github.sherlock.sell.exception;

import com.github.sherlock.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by TangBin on 2017/8/28.
 */

@Getter
public class SellException extends RuntimeException {

  private Integer code;

  public SellException(ResultEnum resultEnum) {
    super(resultEnum.getMsg());
    this.code = resultEnum.getCode();
  }

  public SellException(Integer code, String message) {
    super(message);
    this.code = code;
  }
}
