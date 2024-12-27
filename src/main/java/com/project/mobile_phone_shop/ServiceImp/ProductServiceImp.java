package com.project.mobile_phone_shop.ServiceImp;

import com.project.mobile_phone_shop.Dto.ProductDto;
import com.project.mobile_phone_shop.Dto.ProductImportDto;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.ProductImportHistory;
import com.project.mobile_phone_shop.Repository.ProductImportRepository;
import com.project.mobile_phone_shop.Service.ProductService;
import com.project.mobile_phone_shop.Mapper.Mapper;
import com.project.mobile_phone_shop.Repository.ProductRepository;
import com.project.mobile_phone_shop.Validate.Validate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportRepository importRepository;
    private final Validate validate;
    private final Mapper mapper;

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = validate.setProduct(productDto);
        return productRepository.save(product);
    }

    @Override
    public Product getByProductId(Long id) {
        return validate.ValidateProductNotFound(id);
    }

    @Override
    @Transactional
    public void importProduct(ProductImportDto productImportDto) {
        //save
        Product product = getByProductId(productImportDto.getProductId());
        Product importProduct = validate.ValidateImportProduct(product,productImportDto);
        productRepository.save(importProduct);

        //save import history
        ProductImportHistory importHistory = mapper.mapToProductImportHistory(productImportDto, product);
        importRepository.save(importHistory);
    }

    @Override
    public void setSalePrice(Long productId, BigDecimal price) {
        Product product = getByProductId(productId);
        product.setSalePrice(price);
        productRepository.save(product);
    }
}
