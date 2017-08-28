package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.domainobject.OrderDetail;
import com.github.sherlock.sell.domainobject.OrderMaster;
import com.github.sherlock.sell.domainobject.ProductInfo;
import com.github.sherlock.sell.dto.CartDTO;
import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.repository.OrderDetailRepository;
import com.github.sherlock.sell.repository.OrderMasterRepository;
import com.github.sherlock.sell.service.OrderService;
import com.github.sherlock.sell.service.ProductService;
import com.github.sherlock.sell.utils.KeyUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by TangBin on 2017/8/28.
 */
@Service
public class OrderServiceImpl implements OrderService {

  private final ProductService productService;

  private final OrderDetailRepository orderDetailRepository;

  @Autowired
  private final OrderMasterRepository orderMasterRepository;

  @Autowired
  public OrderServiceImpl(ProductService productService,
      OrderDetailRepository orderDetailRepository,
      OrderMasterRepository orderMasterRepository) {
    this.productService = productService;
    this.orderDetailRepository = orderDetailRepository;
    this.orderMasterRepository = orderMasterRepository;
  }

  @Override
  @Transactional
  public OrderDTO create(OrderDTO orderDTO) {

    BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

//    List<CartDTO> cartDTOList = new ArrayList<>();

    // 1. query product (quantity,price)
    List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();

    String orderId = KeyUtil.genUniqueKey();

    for (OrderDetail orderDetail : orderDetailList) {
      ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
      if (productInfo == null) {
        throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
      }
      // 2. accumulate every product's price
      orderAmount = productInfo.getProductPrice()
          .multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

      BeanUtils.copyProperties(productInfo, orderDetail);
      orderDetail.setDetailId(KeyUtil.genUniqueKey());
      orderDetail.setOrderId(orderId);

      orderDetailRepository.save(orderDetail);
    }

    // 3. insert into database (orderMaster and order Detail)
    OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDTO, orderMaster);
    orderMaster.setOrderId(orderId);
    orderMaster.setOrderAmount(orderAmount);
    orderMasterRepository.save(orderMaster);

    // 4. decrease stock
    List<CartDTO> cartDTOList = orderDetailList.stream()
        .map(it -> new CartDTO(it.getProductId(), it.getProductQuantity()))
        .collect(Collectors.toList());

    productService.decreaseStock(cartDTOList);

    return orderDTO;
  }

  @Override
  public OrderDTO findOne(String orderId) {
    return null;
  }

  @Override
  public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
    return null;
  }

  @Override
  public OrderDTO cancel(OrderDTO orderDTO) {
    return null;
  }

  @Override
  public OrderDTO finish(OrderDTO orderDTO) {
    return null;
  }

  @Override
  public OrderDTO pay(OrderDTO orderDTO) {
    return null;
  }
}
