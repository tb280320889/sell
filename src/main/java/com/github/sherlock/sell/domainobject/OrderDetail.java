package com.github.sherlock.sell.domainobject;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

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
  private Date createTime;
  private Date updateTime;
}
