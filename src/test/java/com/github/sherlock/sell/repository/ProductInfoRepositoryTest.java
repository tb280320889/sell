package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.dataobject.ProductInfo;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by TangBin on 2017/8/27.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {

  @Autowired
  private ProductInfoRepository repository;


  @Test
  public void saveTest() throws Exception {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setProductId("123");
    productInfo.setProductName("omelet");
    productInfo.setProductPrice(new BigDecimal(3.0));
    productInfo.setProductStock(233);
    productInfo.setProductDescription("best for breakfast!");
    productInfo.setCategoryType(2);

    ProductInfo result = repository.save(productInfo);
    Assert.assertNotNull(result);
  }

  @Test
  public void findByProductStatus() throws Exception {

    List<ProductInfo> productInfoList = repository.findByProductStatus(0);
    Assert.assertNotEquals(0, productInfoList.size());

  }

}
