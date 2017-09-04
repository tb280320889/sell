package com.github.sherlock.sell.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by TangBin on 2017/9/4.
 */

@Data
@Component
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlConfig {

  /**
   *
   */
  private String weChatWpAuthorize;

  /**
   *
   */
  private String weChatOpenAuthorize;

  /**
   *
   */
  private String sellUrl;


}
