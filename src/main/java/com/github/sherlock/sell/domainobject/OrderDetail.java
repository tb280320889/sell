package com.github.sherlock.sell.domainobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.sherlock.sell.utils.serializer.TimeStamp2LongSerializer;

import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * Created by TangBin on 2017/8/28.
 */

@Entity
@DynamicUpdate
@Data
public class OrderDetail {

  @Id
  private String detailId;
  private String orderId;
  private String productId;
  private String productName;
  private BigDecimal productPrice;
  private Integer productQuantity;
  private String productIcon;
  @JsonSerialize(using = TimeStamp2LongSerializer.class)
  private Timestamp createTime;
  @JsonSerialize(using = TimeStamp2LongSerializer.class)
  private Timestamp updateTime;
}
