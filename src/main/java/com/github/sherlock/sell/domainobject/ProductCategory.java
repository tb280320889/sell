package com.github.sherlock.sell.domainobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

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

  //FIXME need attention!
  public ProductCategory() {
  }

  public ProductCategory(String categoryName, Integer categoryType) {
    this.categoryName = categoryName;
    this.categoryType = categoryType;
  }

  //  /**
//   *
//   */
//  private Date createTime;
//
//  /**
//   *
//   */
//  private Date updateTime;

}