package com.github.sherlock.sell.controller;

import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.service.OrderService;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by TangBin on 2017/8/31.
 */
@Controller
@RequestMapping("seller/order")
@Slf4j
public class SellerOrderController {

  @Autowired
  private OrderService orderService;


  /**
   * @param page which page, start from 1
   * @param size how many lines would show in one page
   */
  @GetMapping("list")
  public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
      @RequestParam(value = "size", defaultValue = "10") Integer size,
      Map<String, Object> map) {
    final PageRequest pageRequest = new PageRequest(page - 1, size);
    final Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);

    map.put("orderDTOPage", orderDTOPage);

    return new ModelAndView("order/list", map);

  }
}
