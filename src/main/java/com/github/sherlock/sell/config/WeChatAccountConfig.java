package com.github.sherlock.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/8/30.
 */

@Data
@Component
@ConfigurationProperties(prefix = "weChat")
public class WeChatAccountConfig {

  private String mpAppId;
  private String mpAppSecret;

}
