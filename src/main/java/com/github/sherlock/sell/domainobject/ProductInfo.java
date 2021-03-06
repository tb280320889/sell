package com.github.sherlock.sell.domainobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sherlock.sell.enums.ProductStatusEnum;
import com.github.sherlock.sell.utils.EnumUtil;

import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * Created by TangBin on 2017/8/27.
 */
@Entity
@Data
@DynamicUpdate
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
  private Integer productStatus = ProductStatusEnum.UP.getCode();

  /**
   *
   */
  private Integer categoryType;
  /**
   *
   */
  private Timestamp createTime;
  /**
   *
   */
  private Timestamp updateTime;

  @JsonIgnore
  public ProductStatusEnum getProductStatusEnum() {
    return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
  }
}
