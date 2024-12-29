package com.project.mobile_phone_shop.Repository;

import com.project.mobile_phone_shop.Entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail,Long> {
    List<SaleDetail> findBySaleId(Long saleId);
}
