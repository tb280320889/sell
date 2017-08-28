package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.domainobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TangBin on 2017/8/28.
 */

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

  Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);

}
