package com.github.sherlock.sell.service;

import com.github.sherlock.sell.domainobject.ProductCategory;

import java.util.List;

/**
 * Created by TangBin on 2017/8/27.
 */

public interface CategoryService {

  ProductCategory findOne(Integer categoryId);

  List<ProductCategory> findAll();

  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

  ProductCategory save(ProductCategory productCategory);
}
