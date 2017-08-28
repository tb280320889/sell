package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.domainobject.OrderDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TangBin on 2017/8/28.
 */

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

  List<OrderDetail> findByOrOrderId(String buyerOpenId);
}
