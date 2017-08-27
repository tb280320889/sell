package com.github.sherlock.sell.dataobject;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 * Created by TangBin on 2017/8/27.
 */
@Entity
@Data
public class ProductInfo {

  /**
   *
   */
  @Id
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
  private Integer productStatus;

  /**
   *
   */
  private Integer categoryType;
}
