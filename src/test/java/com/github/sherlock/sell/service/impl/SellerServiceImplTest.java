package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.domainobject.SellerInfo;
import com.github.sherlock.sell.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by TangBin on 2017/9/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {

  public static final String openid = "abc";

  @Autowired
  private SellerService sellerService;

  @Test
  public void findSellerInfoByOpenid() throws Exception {

    final SellerInfo result = sellerService.findSellerInfoByOpenid(openid);
    Assert.assertEquals(openid, result.getOpenid());
  }

}
