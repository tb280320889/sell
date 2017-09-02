package com.github.sherlock.sell.domainobject;

import com.github.sherlock.sell.enums.OrderStatusEnum;
import com.github.sherlock.sell.enums.PayStatusEnum;

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
@Data
@DynamicUpdate
public class OrderMaster {

  /**
   *
   */
  @Id
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
  private Timestamp createTime;

  /**
   *
   */
  private Timestamp updateTime;

//  @Transient //FIXME need attention!
//  private List<OrderDetail> orderDetailList;
}
