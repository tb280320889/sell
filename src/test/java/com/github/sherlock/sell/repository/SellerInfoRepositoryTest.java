package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.domainobject.SellerInfo;
import com.github.sherlock.sell.utils.KeyUtil;

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
public class SellerInfoRepositoryTest {

  @Autowired
  private SellerInfoRepository sellerInfoRepository;


  @Test
  public void findByOpenid() throws Exception {

    final SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("abc");

    Assert.assertEquals("abc", sellerInfo.getOpenid());
  }

  @Test
  public void save() throws Exception {
    final SellerInfo sellerInfo = new SellerInfo();
    sellerInfo.setSellerId(KeyUtil.genUniqueKey());
    sellerInfo.setUsername("admin");
    sellerInfo.setPassword("admin");
    sellerInfo.setOpenid("abc");

    final SellerInfo result = sellerInfoRepository.save(sellerInfo);
    Assert.assertNotNull(result);

  }
}
