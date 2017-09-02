package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.domainobject.ProductInfo;
import com.github.sherlock.sell.dto.CartDTO;
import com.github.sherlock.sell.enums.ProductStatusEnum;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.repository.OrderDetailRepository;
import com.github.sherlock.sell.repository.ProductInfoRepository;
import com.github.sherlock.sell.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

/**
 * Created by TangBin on 2017/8/27.
 */

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductInfoRepository productInfoRepository;

  private final OrderDetailRepository orderDetailRepository;

  @Autowired
  public ProductServiceImpl(ProductInfoRepository productInfoRepository,
                            OrderDetailRepository orderDetailRepository) {
    this.productInfoRepository = productInfoRepository;
    this.orderDetailRepository = orderDetailRepository;
  }

  @Override
  public ProductInfo findOne(String productId) {
    return productInfoRepository.findOne(productId);
  }

  @Override
  public List<ProductInfo> findUpAll() {
    return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
  }

  @Override
  public Page<ProductInfo> findAll(Pageable pageable) {
    return productInfoRepository.findAll(pageable);
  }

  @Override
  public ProductInfo save(ProductInfo productInfo) {
    return productInfoRepository.save(productInfo);
  }

  @Override
  @Transactional
  public void increaseStock(List<CartDTO> cartDTOList) {
    for (CartDTO cartDTO : cartDTOList) {
      final ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
      if (productInfo == null) {
        throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
      }
      final int result = productInfo.getProductStock() + cartDTO.getProductQuantity();
      productInfo.setProductStock(result);
      productInfoRepository.save(productInfo);
    }
  }

  @Override
  @Transactional
  public void decreaseStock(List<CartDTO> cartDTOList) {
    for (CartDTO cartDTO : cartDTOList) {
      ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
      if (productInfo == null) {
        throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
      }

      Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
      if (result < 0) {
        throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
      }
      productInfo.setProductStock(result);
      productInfoRepository.save(productInfo);
    }

  }

  @Override
  public ProductInfo onShelf(String productId) {
    final ProductInfo productInfo = productInfoRepository.findOne(productId);
    if (productInfo == null) {
      throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
    }
    if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
      throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
    }
    productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
    return productInfoRepository.save(productInfo);

  }

  @Override
  public ProductInfo offShelf(String productId) {
    final ProductInfo productInfo = productInfoRepository.findOne(productId);
    if (productInfo == null) {
      throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
    }
    if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
      throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
    }
    productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
    return productInfoRepository.save(productInfo);

  }
}
