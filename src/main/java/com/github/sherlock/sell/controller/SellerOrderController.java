package com.github.sherlock.sell.controller;

import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

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
    map.put("currentPage", page);
    map.put("orderDTOPage", orderDTOPage);

    return new ModelAndView("order/list", map);

  }

  /**
   *
   * @param orderId
   * @return
   */
  @GetMapping("cancel")
  public ModelAndView cancel(@RequestParam("orderId") String orderId, Map<String, Object> map) {

    OrderDTO orderDTO;
    map.put("url", "/sell/seller/order/list");
    try {
      orderDTO = orderService.findOne(orderId);
      orderService.cancel(orderDTO);
      map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
      return new ModelAndView("common/success", map);
    } catch (SellException e) {
      log.error("#seller cancel order# can't not find order");
      map.put("msg", e.getMessage());

      return new ModelAndView("common/error", map);
    }

  }

  /**
   *
   * @param orderId
   * @param map
   * @return
   */
  @GetMapping("detail")
  public ModelAndView detail(@RequestParam("orderId") String orderId, Map<String, Object> map) {

    map.put("url", "/sell/seller/order/detail");
    OrderDTO orderDTO;
    try {
      orderDTO = orderService.findOne(orderId);
      map.put("orderDTO", orderDTO);
      return new ModelAndView("order/detail", map);
    } catch (SellException e) {
      map.put("msg", e.getMessage());
      return new ModelAndView("common/error", map);
    }


  }

  /**
   *
   * @param orderId
   * @param map
   * @return
   */
  @GetMapping("finish")
  public ModelAndView finish(@RequestParam("orderId") String orderId, Map<String, Object> map) {
    OrderDTO orderDTO;
    map.put("url", "/sell/seller/order/list");
    try {
      orderDTO = orderService.findOne(orderId);
      orderService.finish(orderDTO);
      map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
      return new ModelAndView("common/success", map);
    } catch (SellException e) {
      log.error("#seller finish order# can't not find order");
      map.put("msg", e.getMessage());

      return new ModelAndView("common/error", map);
    }
  }

}
