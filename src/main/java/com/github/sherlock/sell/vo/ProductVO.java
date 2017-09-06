package com.github.sherlock.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TangBin on 2017/8/28.
 */
@Data
public class ProductVO implements Serializable {

  private static final long serialVersionUID = -6658842205363259590L;
  @JsonProperty("name")
  private String categoryName;

  @JsonProperty("type")
  private Integer categoryType;

  @JsonProperty("foods")
  private List<ProductInfoVO> productInfoVOList;
}
