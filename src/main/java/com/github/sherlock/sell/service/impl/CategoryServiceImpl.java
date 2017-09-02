package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.domainobject.ProductCategory;
import com.github.sherlock.sell.repository.ProductCategoryRepository;
import com.github.sherlock.sell.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TangBin on 2017/8/27.
 */
@Service //FIXME need attention!
public class CategoryServiceImpl implements CategoryService {


  private final ProductCategoryRepository repository;

  @Autowired //FIXME need attention!
  public CategoryServiceImpl(ProductCategoryRepository repository) {
    this.repository = repository;
  }

  /**
   *
   * @param categoryId
   * @return
   */
  @Override
  public ProductCategory findOne(Integer categoryId) {
    return repository.findOne(categoryId);
  }


  /**
   *
   * @return
   */
  @Override
  public List<ProductCategory> findAll() {
    return repository.findAll();
  }

  /**
   *
   * @param categoryTypeList
   * @return
   */
  @Override
  public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
    return repository.findByCategoryTypeIn(categoryTypeList);
  }

  /**
   *
   * @param productCategory
   * @return
   */
  @Override
  public ProductCategory save(ProductCategory productCategory) {
    return repository.save(productCategory);
  }
}
