package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.domainobject.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by TangBin on 2017/8/28.
 */

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

  List<OrderDetail> findByOrderId(String buyerOpenId);
}
