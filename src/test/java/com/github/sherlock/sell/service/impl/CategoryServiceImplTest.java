package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.domainobject.ProductCategory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by TangBin on 2017/8/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

  @Autowired
  private CategoryServiceImpl categoryService;

  @Test
  public void findOne() throws Exception {
    ProductCategory productCategory = categoryService.findOne(1);
    Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
  }

  @Test
  public void findAll() throws Exception {
    List<ProductCategory> productCategoryList = categoryService.findAll();
    Assert.assertNotEquals(0, productCategoryList.size());
  }

  @Test
  public void findByCategoryTypeIn() throws Exception {
    List<ProductCategory> productCategoryList = categoryService
      .findByCategoryTypeIn(Arrays.asList(1, 2, 4, 5));
    Assert.assertNotEquals(0, productCategoryList.size());
  }

  @Test
  public void save() throws Exception {
    ProductCategory productCategory = categoryService.findOne(12); //FIXME need attention!
    productCategory.setCategoryType(15);
    ProductCategory result = categoryService.save(productCategory);
    Assert.assertNotNull(result);
  }

}
