package com.github.sherlock.sell.controller;

import com.github.sherlock.sell.config.ProjectUrlConfig;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * Created by TangBin on 2017/8/30.
 */

@Controller
@RequestMapping("/weChat")
@Slf4j
public class WeChatController {

  private final WxMpService wxMpService;

  private final WxMpService wxOpenService;

  private final ProjectUrlConfig projectUrlConfig;

  @Autowired
  public WeChatController(WxMpService wxMpService, WxMpService wxOpenService, ProjectUrlConfig projectUrlConfig) {
    this.wxMpService = wxMpService;
    this.wxOpenService = wxOpenService;
    this.projectUrlConfig = projectUrlConfig;
  }

  /**
   * @param returnUrl
   * @return
   */
  @GetMapping("authorize")
  public String authorize(@RequestParam("returnUrl") String returnUrl) {

    String url = projectUrlConfig.getWeChatWpAuthorize() + "sell/weChat/userInfo";

    //1.config
    final String redirectUrl = wxMpService
      .oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO,
        URLEncoder.encode(returnUrl));
    log.info("#wechat web auth# get code, redirectUrl={}", redirectUrl);

    //2.call method
    return "redirect:" + redirectUrl;
  }

  /**
   * @param code
   * @param state
   * @return
   */
  @GetMapping("userInfo")
  public String userInfo(@RequestParam("code") String code, @RequestParam("state") String state) {
    WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
    try {
      wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
    } catch (WxErrorException e) {
      log.error("#wechat web auth# {}", e);
      throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
    }

    final String openId = wxMpOAuth2AccessToken.getOpenId();
    return "redirect:" + state + "?openid=" + openId;
  }

  private static final String QR_AUTHORIZE = "qrAuthorize";

  /**
   * @param returnUrl
   * @return
   */
  @GetMapping(QR_AUTHORIZE)
  public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {

    String url = projectUrlConfig.getWeChatOpenAuthorize() + "sell/weChat/qrUserInfo";
    final String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN, URLEncoder.encode(returnUrl));

    return "redirect:" + redirectUrl;
  }

  private static final String QR_USER_INFO = "qrUserInfo";

  /**
   * @param code
   * @param returnUrl
   * @return
   */
  @GetMapping(QR_USER_INFO)
  public String qrUserInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
    WxMpOAuth2AccessToken wxMpOAuth2AccessToken;

    try {
      wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
    } catch (WxErrorException e) {
      log.error("#weChat web auth", e);
      throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
    }

    log.info("wxMpOAuth2AccessToken={}", wxMpOAuth2AccessToken);
    final String openId = wxMpOAuth2AccessToken.getOpenId();

    return "redirect:" + returnUrl + "?openid=" + openId;

  }


}
