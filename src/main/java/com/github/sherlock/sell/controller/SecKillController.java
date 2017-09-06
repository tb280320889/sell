package com.github.sherlock.sell.controller;

import com.github.sherlock.sell.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.sherlock.sell.controller.SecKillController.SEC_KILL;

/**
 * Created by TangBin on 2017/9/6.
 */


@RestController
@RequestMapping(SEC_KILL)
@Slf4j
public class SecKillController {
  static final String SEC_KILL = "sec-kill";
  private static final String QUERY = "query";
  private static final String ORDER = "order";
  private final SecKillService secKillService;

  /**
   *
   */
  @Autowired
  public SecKillController(SecKillService secKillService) {
    this.secKillService = secKillService;
  }

  /**
   * @param productId
   * @return
   */
  @GetMapping(QUERY + "/{productId}")
  public String query(@PathVariable String productId) {

    return secKillService.querySecKillProductInfo(productId);
  }

  /**
   * @param productId
   * @return
   */
  @GetMapping(ORDER + "/{productId}")
  public String order(@PathVariable String productId) {
    log.info("#secKill order# request, productId : {}", productId);
    secKillService.orderProductMockDiffUser(productId);
    return secKillService.querySecKillProductInfo(productId);
  }


}
