package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.domainobject.OrderMaster;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by TangBin on 2017/8/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j  //FIXME need attention!
public class OrderMasterRepositoryTest {

  @Autowired
  private OrderMasterRepository repository;

  private final String OPENID = "2333";

  @Test
  public void findByBuyerOpenid() throws Exception {

    PageRequest request = new PageRequest(0, 1);
    Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);

    Assert.assertNotEquals(0l, result.getTotalElements());

    log.info("getElement : {}, ", result.getTotalElements());
  }


  @Test
  public void saveTest() throws Exception {

    OrderMaster orderMaster = new OrderMaster();
    orderMaster.setOrderId("1234567");
    orderMaster.setBuyerName("sherlock");
    orderMaster.setBuyerPhone("23333333");
    orderMaster.setBuyerAddress("github");
    orderMaster.setBuyerOpenid(OPENID);
    orderMaster.setOrderAmount(new BigDecimal(23));

    OrderMaster result = repository.save(orderMaster);
    Assert.assertNotNull(result);
  }


}
