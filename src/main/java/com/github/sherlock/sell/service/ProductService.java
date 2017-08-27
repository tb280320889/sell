package com.github.sherlock.sell.service;

import com.github.sherlock.sell.dataobject.ProductInfo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by TangBin on 2017/8/27.
 */

public interface ProductService {

  ProductInfo findOne(String productId);

  /**
   * find all products which are on shelves
   */
  List<ProductInfo> findUpAll();

  //FIXME need attention!
  Page<ProductInfo> findAll(Pageable pageable);

  ProductInfo save(ProductInfo productInfo);

  //increase stock

  //decrease stock


}
