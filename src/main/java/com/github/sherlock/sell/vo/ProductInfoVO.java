package com.github.sherlock.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by TangBin on 2017/8/28.
 */
@Data
public class ProductInfoVO implements Serializable {

  private static final long serialVersionUID = 5306901825994201584L;
  @JsonProperty("id")
  private String productId;

  @JsonProperty("name")
  private String productName;

  @JsonProperty("price")
  private String productPrice;

  @JsonProperty("description")
  private String productDescription;

  @JsonProperty("icon")
  private String productIcon;
}

