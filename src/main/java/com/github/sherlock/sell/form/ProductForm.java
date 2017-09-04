package com.github.sherlock.sell.form;

import java.math.BigDecimal;

import lombok.Data;

/**
 * Created by TangBin on 2017/9/4.
 */

@Data
public class ProductForm {

  /**
   *
   */
  private String productId;

  /**
   *
   */
  private String productName;

  /**
   *
   */
  private BigDecimal productPrice;

  /**
   *
   */
  private Integer productStock;

  /**
   *
   */
  private String productDescription;

  /**
   *
   */
  private String productIcon;

  /**
   *
   */
  private Integer categoryType;


}


