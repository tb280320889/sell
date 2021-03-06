package com.github.sherlock.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/8/31.
 */

@Component
public class WeChatPayConfig {

  private final WeChatAccountConfig weChatAccountConfig;


  @Autowired
  public WeChatPayConfig(WeChatAccountConfig weChatAccountConfig) {
    this.weChatAccountConfig = weChatAccountConfig;
  }

  @Bean
  public BestPayServiceImpl bestPayService() {
    WxPayH5Config wxPayH5Config = wePayH5Config();
    final BestPayServiceImpl bestPayService = new BestPayServiceImpl();
    bestPayService.setWxPayH5Config(wxPayH5Config);
    return bestPayService;
  }

  @Bean
  public WxPayH5Config wePayH5Config() {
    final WxPayH5Config wxPayH5Config = new WxPayH5Config();
    wxPayH5Config.setAppId(weChatAccountConfig.getMpAppId());
    wxPayH5Config.setAppSecret(weChatAccountConfig.getMpAppSecret());
    wxPayH5Config.setMchId(weChatAccountConfig.getMchId());
    wxPayH5Config.setMchKey(weChatAccountConfig.getMchKey());
    wxPayH5Config.setMchKey(weChatAccountConfig.getMchKey());
    wxPayH5Config.setNotifyUrl(weChatAccountConfig.getNotifyUrl());
    return wxPayH5Config;
  }

}
