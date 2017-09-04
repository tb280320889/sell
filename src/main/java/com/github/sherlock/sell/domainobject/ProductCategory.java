package com.github.sherlock.sell.domainobject;

import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * Created by TangBin on 2017/8/27.
 */

@Entity
@Data
@DynamicUpdate  //FIXME need attention!
public class ProductCategory {

  /**
   *
   */
  @Id
  @GeneratedValue
  private Integer categoryId;

  /**
   *
   */
  private String categoryName;

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

  //FIXME need attention!
  public ProductCategory() {
  }

  public ProductCategory(String categoryName, Integer categoryType) {
    this.categoryName = categoryName;
    this.categoryType = categoryType;
  }


}
