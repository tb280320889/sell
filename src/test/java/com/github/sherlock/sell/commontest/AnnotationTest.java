package com.github.sherlock.sell.commontest;

import com.github.sherlock.sell.config.ProjectUrlConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/9/4.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AnnotationTest {

  @Autowired
  private ProjectUrlConfig projectUrlConfig;

  @Test
  public void testAnnotation() throws Exception {
    log.info(projectUrlConfig.toString());
  }
}
