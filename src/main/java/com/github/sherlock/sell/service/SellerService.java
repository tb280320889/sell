package com.github.sherlock.sell.service;

import com.github.sherlock.sell.domainobject.SellerInfo;

/**
 * Created by TangBin on 2017/9/4.
 */

public interface SellerService {
  /**
   *
   * @param openid
   * @return
   */
  SellerInfo findSellerInfoByOpenid(String openid);
}
