package com.project.mobile_phone_shop.Repository;

import com.project.mobile_phone_shop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    Product findByName(String name);
    Optional<Product> findByModelIdAndColorId(Long modelId, Long colorId);
}
