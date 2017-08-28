package com.github.sherlock.sell.controller;

import com.github.sherlock.sell.domainobject.ProductCategory;
import com.github.sherlock.sell.domainobject.ProductInfo;
import com.github.sherlock.sell.service.CategoryService;
import com.github.sherlock.sell.service.ProductService;
import com.github.sherlock.sell.utils.ResultVOUtil;
import com.github.sherlock.sell.vo.ProductInfoVO;
import com.github.sherlock.sell.vo.ProductVO;
import com.github.sherlock.sell.vo.ResultVO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TangBin on 2017/8/28.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

  private final ProductService productService;

  private final CategoryService categoryService;

  @Autowired
  public BuyerProductController(ProductService productService, CategoryService categoryService) {
    this.productService = productService;
    this.categoryService = categoryService;
  }

  /**
   *
   * @return
   */
  @GetMapping("/list")
  public ResultVO list() {
    // 1. select all products which are on shelf
    List<ProductInfo> productInfoList = productService.findUpAll();

    // 2. select category which are needed at one time
    List<Integer> categoryTypeList = productInfoList.stream()
        .map(ProductInfo::getCategoryType)
        .collect(Collectors.toList());

    List<ProductCategory> productCategoryList = categoryService
        .findByCategoryTypeIn(categoryTypeList);

    // 3. assemble data

    List<ProductVO> productVOList = new ArrayList<>();
    productCategoryList.forEach(pC -> {
          ProductVO p = new ProductVO();
          p.setCategoryType(pC.getCategoryType());
          p.setCategoryName(pC.getCategoryName());

          List<ProductInfoVO> productInfoVOList = productInfoList.stream()
              .filter(pI -> pI.getCategoryType().equals(pC.getCategoryType()))
              .map(pI -> {
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(pI, productInfoVO);
                return productInfoVO;
              }).collect(Collectors.toList());

          p.setProductInfoVOList(productInfoVOList);
          productVOList.add(p);
        }
    );

    return ResultVOUtil.success(productVOList);
  }
}
