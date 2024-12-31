package com.project.mobile_phone_shop.Repository;

import com.project.mobile_phone_shop.Entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail,Long>, JpaSpecificationExecutor<SaleDetail> {
    List<SaleDetail> findBySaleId(Long saleId);
}
