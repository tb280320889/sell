package com.github.sherlock.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/8/30.
 */

@Component
public class WeChatMpConfig {

  private final WeChatAccountConfig weChatAccountConfig;

  @Autowired
  public WeChatMpConfig(WeChatAccountConfig weChatAccountConfig) {
    this.weChatAccountConfig = weChatAccountConfig;
  }

  @Bean
  public WxMpService wxMpService() {
    WxMpService wxMpService = new WxMpServiceImpl();
    wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
    return wxMpService;
  }

  @Bean
  public WxMpConfigStorage wxMpConfigStorage() {
    final WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();

    wxMpInMemoryConfigStorage.setAppId(weChatAccountConfig.getMpAppId());
    wxMpInMemoryConfigStorage.setSecret(weChatAccountConfig.getMpAppSecret());
    return wxMpInMemoryConfigStorage;
  }

}
