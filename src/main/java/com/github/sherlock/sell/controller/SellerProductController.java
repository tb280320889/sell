package com.github.sherlock.sell.controller;

import com.github.sherlock.sell.domainobject.ProductInfo;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/9/2.
 */

@RestController
@Controller
@RequestMapping("seller/product")
@Slf4j
public class SellerProductController {

  private final ProductService productService;

  @Autowired
  public SellerProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   *
   * @param page
   * @param size
   * @param map
   * @return
   */
  @GetMapping("list")
  public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size, Map<String, Object> map
  ) {
    final PageRequest request = new PageRequest(page - 1, size);
    final Page<ProductInfo> productInfoPage = productService.findAll(request);
    map.put("productInfoPage", productInfoPage);
    map.put("currentPage", page);
    map.put("size", size);
    return new ModelAndView("product/list", map);
  }

  @GetMapping("onSale")
  public ModelAndView onSale(@RequestParam("productId") String productId, Map<String, Object> vMap) {
    vMap.put("url", "sell/seller/product/list");
    try {
      productService.onShelf(productId);
    } catch (SellException e) {
      vMap.put("msg", e.getMessage());
      return new ModelAndView("common/error", vMap);
    }
    return new ModelAndView("common/success", vMap);
  }

  @GetMapping("offSale")
  public ModelAndView offSale(@RequestParam("productId") String productId, Map<String, Object> vMap) {
    vMap.put("url", "sell/seller/product/list");
    try {
      productService.offShelf(productId);
    } catch (SellException e) {
      vMap.put("msg", e.getMessage());
      return new ModelAndView("common/error", vMap);
    }
    return new ModelAndView("common/success", vMap);
  }

}
