package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.service.SecKillService;
import com.github.sherlock.sell.service.lock.RedisLock;
import com.github.sherlock.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TangBin on 2017/9/6.
 */

@Service
@Slf4j
public class SecKillServiceImpl implements SecKillService {
  private static final Integer TIMEOUT = 10;
  private static Map<String, Integer> products;
  private static Map<String, Integer> stock;
  private static Map<String, String> orders;
  private static RLock lock;

  static {
    products = new HashMap<>();
    stock = new HashMap<>();
    orders = new HashMap<>();
    products.put("123456", 100000);
    stock.put("123456", 100000);
    Config config2 = new Config();
    config2.useSingleServer().setAddress("http://192.168.1.8:6379");
    RedissonClient redissonClient = Redisson.create(config2);
    lock = redissonClient.getLock("TEST");

  }

  private final RedisLock redisLock;


  @Autowired
  public SecKillServiceImpl(RedisLock redisLock) {
    this.redisLock = redisLock;

  }

  private String queryMap(String productId) {
    return "festival, discount for some goods, quantity limited "
      + products.get(productId)
      + " left : " + stock.get(productId)
      + " order successfully user number : "
      + orders.size();
  }

  @Override
  public String querySecKillProductInfo(String productId) {
    return this.queryMap(productId);

  }

  @Override
  public void orderProductMockDiffUser(String productId) {

    //add lock
//    Long time = System.currentTimeMillis() + TIMEOUT;
//    if (!redisLock.lock(productId, String.valueOf(time))) {
//      throw new SellException(101, "too much users, sorry for missing chance, you can try again");
//    }


    try {
      lock.lock();
      // query the stock left of the product
      Integer stockNum = stock.get(productId);
      if (stockNum == 0) {
        throw new SellException(100, "discount over");

      } else {
        // place an order (mock different openid)
        orders.put(KeyUtil.genUniqueKey(), productId);
        stockNum = stockNum - 1;
        try {
          Thread.sleep(100);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        stock.put(productId, stockNum);
      }
      //unlock
//    redisLock.unlock(productId, String.valueOf(time));
    } catch (Exception e) {
      log.error("#Redisson lock# {}", e.getMessage());
    } finally {
      lock.unlock();
    }
  }


}
