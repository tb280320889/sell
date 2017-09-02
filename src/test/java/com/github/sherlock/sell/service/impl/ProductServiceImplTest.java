package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.domainobject.ProductInfo;
import com.github.sherlock.sell.enums.ProductStatusEnum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by TangBin on 2017/8/27.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

  @Autowired
  private ProductServiceImpl service;

  @Test
  public void findOne() throws Exception {
    ProductInfo productInfo = service.findOne("1234");
    Assert.assertEquals("1234", productInfo.getProductId());
  }

  @Test
  public void findUpAll() throws Exception {
    List<ProductInfo> productInfoList = service.findUpAll();
    Assert.assertNotEquals(0, productInfoList.size());
  }

  @Test
  public void findAll() throws Exception {
    PageRequest request = new PageRequest(0, 2);
    Page<ProductInfo> productInfoPage = service.findAll(request);
    System.out.println(productInfoPage.getTotalElements());
  }

  @Test
  public void save() throws Exception {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setProductId("1234");
    productInfo.setProductName("testName");
    productInfo.setProductPrice(new BigDecimal(2.33));
    productInfo.setProductStock(233);
    productInfo.setProductDescription("nice shrimp");
    productInfo.setProductIcon("http://iconUrl.jpg");
    productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
    productInfo.setCategoryType(2);

    ProductInfo result = service.save(productInfo);
    Assert.assertNotNull(result);

  }

}
