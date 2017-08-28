package com.github.sherlock.sell.repository;

import com.github.sherlock.sell.domainobject.ProductInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TangBin on 2017/8/27.
 */

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

  List<ProductInfo> findByProductStatus(Integer productStatus);
}
