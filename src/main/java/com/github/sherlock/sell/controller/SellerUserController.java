package com.github.sherlock.sell.controller;

import com.github.sherlock.sell.config.ProjectUrlConfig;
import com.github.sherlock.sell.constant.CookieConstant;
import com.github.sherlock.sell.constant.RedisConstant;
import com.github.sherlock.sell.domainobject.SellerInfo;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.service.SellerService;
import com.github.sherlock.sell.utils.CookieUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/9/4.
 */

@Controller
@RequestMapping("seller/")
@Slf4j
public class SellerUserController {
  static final String SellerUser = "SellerUser";

  private SellerService sellerService;

  private StringRedisTemplate stringRedisTemplate;

  private final ProjectUrlConfig projectUrlConfig;

  private static final String LOGIN = "login";

  /**
   *
   * @param sellerService
   * @param stringRedisTemplate
   * @param projectUrlConfig
   */
  @Autowired
  public SellerUserController(SellerService sellerService, StringRedisTemplate stringRedisTemplate, ProjectUrlConfig projectUrlConfig) {
    this.sellerService = sellerService;
    this.stringRedisTemplate = stringRedisTemplate;
    this.projectUrlConfig = projectUrlConfig;
  }

  /**
   *
   * @param openid
   * @param vMap
   * @return
   */
  @GetMapping(LOGIN)
  public ModelAndView login(@RequestParam("openid") String openid, HttpServletResponse httpServletResponse, Map<String, Object> vMap) {
    final SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
    //check openid
    if (sellerInfo == null) {
      vMap.put("msg", ResultEnum.LOGIN_FAIL);
      vMap.put("url", "sell/seller/order/list");
      return new ModelAndView("common/error");
    }

    //set token into redis
    final String token = UUID.randomUUID().toString();
    stringRedisTemplate.opsForValue()
      .set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, RedisConstant.EXPIRE, TimeUnit.SECONDS);

    //set token to cookie
    CookieUtil.set(httpServletResponse, CookieConstant.TOKEN, token, RedisConstant.EXPIRE);
    return new ModelAndView("redirect:" + projectUrlConfig.getSellUrl() + "sell/seller/order/list");
  }

  private static final String LOGOUT = "logout";

  /**
   *  @param request
   * @param response
   * @param vMap
   */
  @GetMapping(LOGOUT)
  public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> vMap) {
    // query cookie
    final Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
    if (cookie != null) {
      // delete info in redis
      stringRedisTemplate.opsForValue().getOperations()
        .delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
      // delete cookie
      CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
    }
    vMap.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
    vMap.put("url", "sell/seller/order/list");
    return new ModelAndView("common/success", vMap);
  }

}
