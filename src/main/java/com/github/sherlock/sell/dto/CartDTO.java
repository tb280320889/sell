package com.github.sherlock.sell.dto;

import lombok.Data;

/**
 * Created by TangBin on 2017/8/28.
 */
@Data
public class CartDTO {

  /**
   *
   */
  private String productId;

  /**
   *
   */
  private Integer productQuantity;

  public CartDTO(String productId, Integer productQuantity) {
    this.productId = productId;
    this.productQuantity = productQuantity;
  }
}
