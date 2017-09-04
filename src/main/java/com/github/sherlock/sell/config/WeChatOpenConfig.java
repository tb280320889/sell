package com.github.sherlock.sell.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * Created by TangBin on 2017/8/30.
 */

@Component
public class WeChatOpenConfig {

  private final WeChatAccountConfig weChatAccountConfig;

  @Autowired
  public WeChatOpenConfig(WeChatAccountConfig weChatAccountConfig) {
    this.weChatAccountConfig = weChatAccountConfig;
  }

  @Bean
  public WxMpService wxOpenService() {
    WxMpService wxOpenService = new WxMpServiceImpl();
    wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
    return wxOpenService;
  }

  @Bean
  public WxMpConfigStorage wxOpenConfigStorage() {
    final WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();

    wxMpInMemoryConfigStorage.setAppId(weChatAccountConfig.getOpenAppId());
    wxMpInMemoryConfigStorage.setSecret(weChatAccountConfig.getOpenAppSecret());
    return wxMpInMemoryConfigStorage;
  }

}
