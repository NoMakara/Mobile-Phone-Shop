package com.project.mobile_phone_shop.Repository;

import com.project.mobile_phone_shop.Entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportRepository extends JpaRepository<ProductImportHistory,Long>{
}
