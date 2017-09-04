package com.github.sherlock.sell.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by TangBin on 2017/8/30.
 */

@Data
@Component
@ConfigurationProperties(prefix = "weChat")
public class WeChatAccountConfig {

  private String mpAppId;
  private String mpAppSecret;
  private String mchId;
  private String mchKey;
  private String keyPath;
  private String notifyUrl;
  private String openAppId;
  private String openAppSecret;

}
