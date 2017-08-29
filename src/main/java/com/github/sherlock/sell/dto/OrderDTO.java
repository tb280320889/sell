package com.github.sherlock.sell.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.sherlock.sell.domainobject.OrderDetail;
import com.github.sherlock.sell.enums.OrderStatusEnum;
import com.github.sherlock.sell.enums.PayStatusEnum;
import com.github.sherlock.sell.utils.serializer.TimeStamp2LongSerializer;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import lombok.Data;


/**
 * Created by TangBin on 2017/8/28.
 */
@Data
//FIXME need attention!
//@JsonInclude(Include.NON_NULL)
public class OrderDTO {

  private String orderId;
  /**
   *
   */
  private String buyerName;
  /**
   *
   */
  private String buyerPhone;
  /**
   *
   */
  private String buyerAddress;
  /**
   *
   */
  private String buyerOpenid;
  /**
   *
   */
  private BigDecimal orderAmount;
  /**
   *
   */
  private Integer orderStatus = OrderStatusEnum.NEW.getCode();
  /**
   *
   */
  private Integer payStatus = PayStatusEnum.WAIT.getCode();

  /**
   *
   */ //FIXME need attention!
  @JsonSerialize(using = TimeStamp2LongSerializer.class)
  private Timestamp createTime;

  /**
   *
   */
  @JsonSerialize(using = TimeStamp2LongSerializer.class)
  private Timestamp updateTime;

  private List<OrderDetail> orderDetailList;

}
