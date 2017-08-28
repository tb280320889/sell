package com.github.sherlock.sell.dto;

import com.github.sherlock.sell.domainobject.OrderDetail;
import com.github.sherlock.sell.enums.OrderStatusEnum;
import com.github.sherlock.sell.enums.PayStatusEnum;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import lombok.Data;


/**
 * Created by TangBin on 2017/8/28.
 */
@Data
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
   */
  private Date createTime;

  /**
   *
   */
  private Date updateTime;

  private List<OrderDetail> orderDetailList;

}
