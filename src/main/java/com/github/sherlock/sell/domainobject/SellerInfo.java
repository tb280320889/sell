package com.github.sherlock.sell.domainobject;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * Created by TangBin on 2017/9/4.
 */

@Entity
@Data
@DynamicUpdate
public class SellerInfo {
  public SellerInfo() {
  }

  /**
   *
   */
  @Id
  private String sellerId;

  /**
   *
   */
  private String username;
  /**
   *
   */
  private String password;
  /**
   *
   */

  private String openid;
//  /**
//   *
//   */
//  private Timestamp createTime;
//
//  /**
//   *
//   */
//  private Timestamp updateTime;

}

