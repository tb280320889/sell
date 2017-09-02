package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.domainobject.ProductCategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by TangBin on 2017/8/27.
 */

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);


}
