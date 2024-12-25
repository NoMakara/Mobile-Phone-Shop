package com.project.mobile_phone_shop.IService;

import com.project.mobile_phone_shop.Dto.ProductDto;
import com.project.mobile_phone_shop.Entity.Product;

public interface ProductService {
    Product createProduct(ProductDto productDto);
    Product getByProductId(Long id);
}
