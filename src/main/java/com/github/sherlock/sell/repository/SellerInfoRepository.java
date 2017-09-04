package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.domainobject.SellerInfo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TangBin on 2017/9/4.
 */

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
  SellerInfo findByOpenid(String openid);
}
