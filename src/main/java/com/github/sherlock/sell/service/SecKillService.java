package com.github.sherlock.sell.service;

/**
 * Created by TangBin on 2017/9/6.
 */

public interface SecKillService {
  String querySecKillProductInfo(String productId);

  void orderProductMockDiffUser(String productId);
}
