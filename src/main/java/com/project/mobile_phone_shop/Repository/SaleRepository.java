package com.project.mobile_phone_shop.Repository;

import com.project.mobile_phone_shop.Entity.Sale;
import com.project.mobile_phone_shop.Projection.ProductSold;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
