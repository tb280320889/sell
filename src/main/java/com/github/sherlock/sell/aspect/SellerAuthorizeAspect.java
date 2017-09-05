package com.github.sherlock.sell.aspect;

import com.github.sherlock.sell.constant.CookieConstant;
import com.github.sherlock.sell.constant.RedisConstant;
import com.github.sherlock.sell.exception.SellerAuthorizeException;
import com.github.sherlock.sell.utils.CookieUtil;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/9/5.
 */

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

  private final StringRedisTemplate stringRedisTemplate;

  @Autowired
  public SellerAuthorizeAspect(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate;
  }


  @Pointcut("execution(public * com.github.sherlock.sell.controller*.*(..)) && !execution(public * com.github.sherlock.sell.controller.SellerUserController.*(..))")
  public void verify() {
  }

  /**
   *
   */
  @Before("verify()")
  public void doVerify() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

    final HttpServletRequest request = attributes.getRequest();

    //check cookie
    final Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
    if (cookie == null) {
      log.warn("#login authorization# cookie can't find token");
      throw new SellerAuthorizeException();
    }

    //query from redis
    final String tokenValue = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

    if (StringUtils.isEmpty(tokenValue)) {
      log.warn("#login authorization# redis can't find token");
      throw new SellerAuthorizeException();
    }


  }


}
