package com.github.sherlock.sell.controller;

import com.github.sherlock.sell.domainobject.ProductCategory;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.form.CategoryForm;
import com.github.sherlock.sell.service.CategoryService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import static com.github.sherlock.sell.controller.SellerCategoryController.CATEGORY;

/**
 * Created by TangBin on 2017/9/4.
 */

@Controller
@RequestMapping("seller/" + CATEGORY)
@Slf4j
public class SellerCategoryController {

  static final String CATEGORY = "category";

  private final CategoryService categoryService;

  @Autowired
  public SellerCategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  private static final String LIST = "list";

  /**
   *
   * @param vMap
   * @return
   */
  @GetMapping(LIST)
  public ModelAndView list(Map<String, Object> vMap) {
    final List<ProductCategory> categoryList = categoryService.findAll();
    vMap.put("categoryList", categoryList);
    return new ModelAndView(CATEGORY + "/" + LIST, vMap);
  }

  private static final String INDEX = "index";

  /**
   *
   * @param categoryId
   * @param vMap
   * @return
   */
  @GetMapping(INDEX)
  public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId, Map<String, Object> vMap) {
    if (categoryId > 0) {
      final ProductCategory productCategory = categoryService.findOne(categoryId);
      vMap.put("productCategory", productCategory);
    }

    return new ModelAndView(CATEGORY + "/" + INDEX, vMap);
  }

  private static final String SAVE = "save";

  /**
   *
   * @param categoryForm
   * @param bindingResult
   * @param vMap
   * @return
   */
  @PostMapping(SAVE)
  public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult, Map<String, Object> vMap) {
    final String msg = "msg";
    final String url = "url";
    if (bindingResult.hasErrors()) {
      vMap.put(msg, bindingResult.getFieldError().getDefaultMessage());
      vMap.put(url, "sell/seller/category/index");
      return new ModelAndView("common/error", vMap);
    }
    ProductCategory productCategory = new ProductCategory();
    try {
      if (categoryForm.getCategoryId() == null) {
        productCategory = categoryService.findOne(categoryForm.getCategoryId());
      }
      BeanUtils.copyProperties(categoryForm, productCategory);
      categoryService.save(productCategory);
    } catch (SellException e) {
      vMap.put(msg, e.getMessage());
      vMap.put(url, "sell/seller/category/index");
      return new ModelAndView("common/error", vMap);
    }
    vMap.put(url, "sell/seller/category/list");
    return new ModelAndView("common/success", vMap);
  }

}
