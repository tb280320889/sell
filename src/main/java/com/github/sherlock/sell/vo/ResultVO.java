package com.github.sherlock.sell.vo;

import lombok.Data;

/**
 * Created by TangBin on 2017/8/28.
 */
@Data
public class ResultVO {

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
  private Object data;
}
