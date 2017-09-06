package com.github.sherlock.sell.service.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by TangBin on 2017/9/6.
 */
@Component
@Slf4j
public class RedisLock {
  private final StringRedisTemplate stringRedisTemplate;

  @Autowired
  public RedisLock(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate;
  }

  /**
   * @param key
   * @param value
   * @return
   */
  public boolean lock(String key, String value) {

    if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {
      return true;
    }
    String currentValue = stringRedisTemplate.opsForValue().get(key);
    //if lock is expired
    if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
      //get last lock timestamp
      final String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
      if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param key
   * @param value
   */
  public void unlock(String key, String value) {
    try {
      final String currentValue = stringRedisTemplate.opsForValue().get(key);
      if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
        stringRedisTemplate.opsForValue().getOperations().delete(key);
      }
    } catch (Exception e) {
      log.error("#redis distributed lock# exception while unlocking ,{}", e);
    }
  }
}
