package com.github.sherlock.sell.form;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * Created by TangBin on 2017/8/29.
 */

@Data
public class OrderForm {

  /**
   *
   */
  @NotEmpty(message = "name required")
  private String name;

  /**
   *
   */
  @NotEmpty(message = "phone number required")
  private String phone;

  /**
   *
   */
  @NotEmpty(message = "address required")
  private String address;

  /**
   * buyer weChat openId
   */
  @NotEmpty(message = "openid required")
  private String openid;

  /**
   *
   */
  @NotEmpty(message = "cart empty")
  private String items;

}
