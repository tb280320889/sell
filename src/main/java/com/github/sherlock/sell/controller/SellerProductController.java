package com.github.sherlock.sell.controller;

import com.github.sherlock.sell.domainobject.ProductCategory;
import com.github.sherlock.sell.domainobject.ProductInfo;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.form.ProductForm;
import com.github.sherlock.sell.service.CategoryService;
import com.github.sherlock.sell.service.ProductService;
import com.github.sherlock.sell.utils.KeyUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by TangBin on 2017/9/2.
 */

@RestController
@Controller
@RequestMapping("seller/product")
@Slf4j
public class SellerProductController {

  private static final String COMMON_ERROR = "common/error";
  private static final String COMMON_SUCCESS = "common/success";
  private final ProductService productService;

  private final CategoryService categoryService;


  @Autowired
  public SellerProductController(ProductService productService, CategoryService categoryService) {
    this.productService = productService;
    this.categoryService = categoryService;
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

  /**
   *
   * @param productId
   * @param vMap
   * @return
   */
  @RequestMapping("on_Sale")
  public ModelAndView onSale(@RequestParam("productId") String productId, Map<String, Object> vMap) {
    vMap.put("url", "sell/seller/product/list");
    try {
      productService.onShelf(productId);
    } catch (SellException e) {
      vMap.put("msg", e.getMessage());
      return new ModelAndView(COMMON_ERROR, vMap);
    }
    return new ModelAndView(COMMON_SUCCESS, vMap);
  }

  /**
   *
   * @param productId
   * @param vMap
   * @return
   */
  @RequestMapping("off_Sale")
  public ModelAndView offSale(@RequestParam("productId") String productId, Map<String, Object> vMap) {
    vMap.put("url", "sell/seller/product/list");
    try {
      productService.offShelf(productId);
    } catch (SellException e) {
      vMap.put("msg", e.getMessage());
      return new ModelAndView(COMMON_ERROR, vMap);
    }
    return new ModelAndView(COMMON_SUCCESS, vMap);
  }

  /**
   *
   * @param productId
   * @param vMap
   * @return
   */
  @GetMapping("index")
  public ModelAndView index(@RequestParam(value = "productId", required = false) String productId, Map<String, Object> vMap) {
    if (!StringUtils.isEmpty(productId)) {
      final ProductInfo productInfo = productService.findOne(productId);
      vMap.put("productInfo", productInfo);

    }
    // query all categories
    final List<ProductCategory> categoryList = categoryService.findAll();

    vMap.put("categoryList", categoryList);
    return new ModelAndView("product/index", vMap);
  }

  /**
   *  @param productForm
   * @param bindingResult
   * @param vMap
   */
  @PostMapping("save")
  public ModelAndView save(@Valid ProductForm productForm, BindingResult bindingResult, Map<String, Object> vMap) {
    if (bindingResult.hasErrors()) {
      vMap.put("msg", bindingResult.getFieldError().getDefaultMessage());
      vMap.put("url", "sell/seller/product/index");
      return new ModelAndView(COMMON_ERROR, vMap);
    }

    ProductInfo productInfo = new ProductInfo();
    try {
      final String productId = productForm.getProductId();
      if (StringUtils.isEmpty(productId)) {
        productForm.setProductId(KeyUtil.genUniqueKey());
      } else {
        productInfo = productService.findOne(productId);
      }
      BeanUtils.copyProperties(productForm, productInfo);

      productService.save(productInfo);
    } catch (SellException e) {
      vMap.put("msg", e.getMessage());
      vMap.put("url", "sell/seller/product/index");
      return new ModelAndView(COMMON_ERROR, vMap);
    }

    vMap.put("url", "sell/seller/product/list");
    return new ModelAndView(COMMON_SUCCESS, vMap);
  }


}
