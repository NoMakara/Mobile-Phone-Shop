package com.project.mobile_phone_shop.Service;

import com.project.mobile_phone_shop.Dto.ProductDto;
import com.project.mobile_phone_shop.Dto.ProductImportDto;
import com.project.mobile_phone_shop.Entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

public interface ProductService {
    Product createProduct(ProductDto productDto);
    Product getByProductId(Long id);
    void importProduct(ProductImportDto productImportDto);
    void setSalePrice(Long productId, BigDecimal price);
    Map<Integer,String> uploadProduct(MultipartFile file);
    Product getByModelIdAndColorId(Long modelId, Long colorId);
}
