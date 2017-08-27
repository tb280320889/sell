package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.dataobject.ProductInfo;
import com.github.sherlock.sell.enums.ProductStatusEnum;
import com.github.sherlock.sell.repository.ProductInfoRepository;
import com.github.sherlock.sell.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Created by TangBin on 2017/8/27.
 */

@Component
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductInfoRepository repository;

  @Override
  public ProductInfo findOne(String productId) {
    return repository.findOne(productId);
  }

  @Override
  public List<ProductInfo> findUpAll() {
    return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
  }

  @Override
  public Page<ProductInfo> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public ProductInfo save(ProductInfo productInfo) {
    return repository.save(productInfo);
  }
}
