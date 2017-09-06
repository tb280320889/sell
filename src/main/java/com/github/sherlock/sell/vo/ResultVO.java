package com.github.sherlock.sell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by TangBin on 2017/8/28.
 */
@Data
public class ResultVO<T> implements Serializable {

  private static final long serialVersionUID = -8586637584828992081L;
  /**
   * response code
   */
  private Integer code;
  /**
   * message
   */
  private String msg;
  /**
   * full data
   */
  private T data;
}
