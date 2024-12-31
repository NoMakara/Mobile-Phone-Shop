package com.project.mobile_phone_shop.Repository;

import com.project.mobile_phone_shop.Entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImportHistoryRepository extends JpaRepository<ProductImportHistory,Long>, JpaSpecificationExecutor<ProductImportHistory> {
}
