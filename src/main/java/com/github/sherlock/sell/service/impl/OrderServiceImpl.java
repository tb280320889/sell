package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.converter.OrderMaster2OrderDTOConverter;
import com.github.sherlock.sell.domainobject.OrderDetail;
import com.github.sherlock.sell.domainobject.OrderMaster;
import com.github.sherlock.sell.domainobject.ProductInfo;
import com.github.sherlock.sell.dto.CartDTO;
import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.enums.OrderStatusEnum;
import com.github.sherlock.sell.enums.PayStatusEnum;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.repository.OrderDetailRepository;
import com.github.sherlock.sell.repository.OrderMasterRepository;
import com.github.sherlock.sell.service.OrderService;
import com.github.sherlock.sell.service.PayService;
import com.github.sherlock.sell.service.ProductService;
import com.github.sherlock.sell.utils.KeyUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Created by TangBin on 2017/8/28.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

  private final ProductService productService;
  private final OrderDetailRepository orderDetailRepository;
  private final OrderMasterRepository orderMasterRepository;
  private final PayService payService;


  @Autowired
  public OrderServiceImpl(ProductService productService,
      OrderDetailRepository orderDetailRepository,
      OrderMasterRepository orderMasterRepository, PayService payService) {
    this.productService = productService;
    this.orderDetailRepository = orderDetailRepository;
    this.orderMasterRepository = orderMasterRepository;
    this.payService = payService;
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
    orderDTO.setOrderId(orderId);
    BeanUtils.copyProperties(orderDTO, orderMaster);
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
    OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
    if (orderMaster == null) {
      throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }
    List<OrderDetail> orderDetailList = orderDetailRepository.findByOrOrderId(orderId);
    if (CollectionUtils.isEmpty(orderDetailList)) {
      throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }
    OrderDTO orderDTO = new OrderDTO();
    BeanUtils.copyProperties(orderMaster, orderDTO);
    orderDTO.setOrderDetailList(orderDetailList);
    return orderDTO;
  }

  @Override
  public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
    Page<OrderMaster> orderMasterPage = orderMasterRepository
        .findByBuyerOpenid(buyerOpenid, pageable);

    List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter
        .convert(orderMasterPage.getContent());

    return new PageImpl<>(orderDTOList, pageable,
        orderMasterPage.getTotalElements());

  }

  @Override
  public Page<OrderDTO> findList(Pageable pageable) {
    final Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
    final List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter
        .convert(orderMasterPage.getContent());

    return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
  }

  @Override
  @Transactional
  public OrderDTO cancel(OrderDTO orderDTO) {
    OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDTO, orderMaster);

    final Integer orderStatus = orderMaster.getOrderStatus();
    final String orderId = orderMaster.getOrderId();
    final List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();

    // analyze status of order
    if (!orderStatus.equals(OrderStatusEnum.NEW.getCode())) {
      log.error("#cancel order# order status is not correct , orderId = {} , order status = {}",
          orderId, orderStatus);
      throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }

    // modify status of order
    orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
    OrderMaster updateResult = orderMasterRepository.save(orderMaster);
    if (updateResult == null) {
      log.error("#cancel order# update order failed , orderId = {} ", orderId);
      throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }
    orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
    // increase stock
    if (CollectionUtils.isEmpty(orderDetailList)) {
      log.error("#cancel order# orderDTO = {} ", orderDTO);
      throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
    }
    final List<CartDTO> cartDTOList = orderDetailList.stream()
        .map(it -> new CartDTO(it.getProductId(), it.getProductQuantity()))
        .collect(Collectors.toList());
    productService.increaseStock(cartDTOList);

    // if paid, give refund
    if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
      payService.refund(orderDTO);
    }

    return orderDTO;
  }

  @Override
  @Transactional
  public OrderDTO finish(OrderDTO orderDTO) {

    // analyze order status
    if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
      log.error("#finish order# order status is not correct, orderId = {},orderStatus = {} ",
          orderDTO.getOrderId(), orderDTO.getOrderStatus());
      throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }
    // modify order status
    orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
    final OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDTO, orderMaster);
    final OrderMaster updateResult = orderMasterRepository.save(orderMaster);
    if (updateResult == null) {
      log.error("finish order# update order failed , orderId = {}", orderMaster.getOrderId());
      throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }
    return orderDTO;
  }

  @Override
  @Transactional
  public OrderDTO pay(OrderDTO orderDTO) {
    // analyze order status
    final String orderId = orderDTO.getOrderId();
    final Integer orderStatus = orderDTO.getOrderStatus();
    final Integer payStatus = orderDTO.getPayStatus();

    if (!orderStatus.equals(OrderStatusEnum.NEW.getCode())) {
      log.error("#pay order# order status is not correct , orderId = {}, order status = {} ",
          orderId, orderStatus);
      throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }
    // analyze pay status

    if (!payStatus.equals(PayStatusEnum.WAIT.getCode())) {
      log.error("#pay order# order pay status is not correct , orderId = {} , payStatus = {} ",
          orderId, payStatus);
      throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
    }
    // modify pay status
    orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
    final OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDTO, orderMaster);
    final OrderMaster updateResult = orderMasterRepository.save(orderMaster);
    if (updateResult == null) {
      log.error("pay order# update order failed , orderId = {}", orderMaster.getOrderId());
      throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }
    return orderDTO;
  }
}
