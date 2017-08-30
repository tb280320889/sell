package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.domainobject.OrderDetail;
import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by TangBin on 2017/8/28.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Test
  @Transactional
  public void saveTest() throws Exception {
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setDetailId("12324");
    orderDetail.setOrderId("111131");
    orderDetail.setProductIcon("http://xxxx.png");
    orderDetail.setProductId("111312");
    orderDetail.setProductName("omelet");
    orderDetail.setProductPrice(new BigDecimal(1.2));
    orderDetail.setProductQuantity(4);

    OrderDetail result = orderDetailRepository.save(orderDetail);
    Assert.assertNotNull(result);
  }

  @Test
  public void findByOrOrderId() throws Exception {
    List<OrderDetail> orderDetailList = orderDetailRepository.findByOrOrderId("111131");

    Assert.assertNotEquals(0, orderDetailList.size());
  }

}
