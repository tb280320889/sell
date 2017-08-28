package com.github.sherlock.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * Created by TangBin on 2017/8/28.
 */
@Data
public class ProductVO {

  @JsonProperty("name")
  private String categoryName;

  @JsonProperty("type")
  private Integer categoryType;

  @JsonProperty("foods")
  private List<ProductInfoVO> productInfoVOList;
}
