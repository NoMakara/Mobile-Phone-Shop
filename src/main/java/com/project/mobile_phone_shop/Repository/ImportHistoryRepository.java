package com.project.mobile_phone_shop.Repository;

import com.project.mobile_phone_shop.Entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportHistoryRepository extends JpaRepository<ProductImportHistory,Long> {
}
