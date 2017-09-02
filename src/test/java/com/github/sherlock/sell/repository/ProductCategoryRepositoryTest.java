package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.domainobject.ProductCategory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

/**
 * Created by TangBin on 2017/8/27.
 */

@RunWith(SpringRunner.class) //FIXME need attention!
@SpringBootTest
public class ProductCategoryRepositoryTest {


  @Autowired
  private ProductCategoryRepository repository;

  @Test
  public void findOneTest() {
    ProductCategory productCategory = repository.findOne(1);
    System.out.println(productCategory.toString());
  }

  @Test
  @Transactional //FIXME need attention!
  public void saveTest() throws Exception {
    ProductCategory productCategory = new ProductCategory();
    productCategory.setCategoryId(2);
    productCategory.setCategoryName("vegetables");
    productCategory.setCategoryType(4);
    ProductCategory result = repository.save(productCategory);
    Assert.assertNotNull(result);
//    Assert.assertNotEquals(null, result);
  }

  @Test
  public void findByCategoryTypeIn() throws Exception {
    List<Integer> categoryList = Collections.singletonList(3);
    List<ProductCategory> result = repository.findByCategoryTypeIn(categoryList);
    Assert.assertNotEquals(0, result.size());
  }

}
