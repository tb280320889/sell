package com.github.sherlock.sell.service;

import com.github.sherlock.sell.domainobject.ProductInfo;
import com.github.sherlock.sell.dto.CartDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
  void increaseStock(List<CartDTO> cartDTOList);

  //decrease stock
  void decreaseStock(List<CartDTO> cartDTOList);

  //onShelf
  ProductInfo onShelf(String productId);

  //offShelf
  ProductInfo offShelf(String productId);
}
