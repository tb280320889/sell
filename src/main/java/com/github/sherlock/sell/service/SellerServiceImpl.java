package com.github.sherlock.sell.service;

import com.github.sherlock.sell.domainobject.SellerInfo;
import com.github.sherlock.sell.repository.SellerInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by TangBin on 2017/9/4.
 */

@Service
public class SellerServiceImpl implements SellerService {

  private final SellerInfoRepository sellerInfoRepository;

  @Autowired
  public SellerServiceImpl(SellerInfoRepository sellerInfoRepository) {
    this.sellerInfoRepository = sellerInfoRepository;
  }


  @Override
  public SellerInfo findSellerInfoByOpenid(String openid) {
    return sellerInfoRepository.findByOpenid(openid);
  }
}
