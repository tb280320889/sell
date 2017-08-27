package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.dataobject.ProductCategory;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
  @Transactional
  public void saveTest() throws Exception {
    ProductCategory productCategory = new ProductCategory();
    productCategory.setCategoryId(2);
    productCategory.setCategoryName("vegetables");
    productCategory.setCategoryType(3);
    ProductCategory result = repository.save(productCategory);
    Assert.assertNotNull(result);
//    Assert.assertNotEquals(null, result);
  }

  @Test
  public void findByCategoryTypeIn() throws Exception {
    List<Integer> categoryList = Arrays.asList(2, 3, 4);
    List<ProductCategory> result = repository.findByCategoryTypeIn(categoryList);
    Assert.assertNotEquals(0, result.size());
  }

}
