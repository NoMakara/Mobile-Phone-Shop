package com.project.mobile_phone_shop.ServiceImp;

import com.project.mobile_phone_shop.Dto.ProductDto;
import com.project.mobile_phone_shop.Entity.Color;
import com.project.mobile_phone_shop.Entity.Model;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.IService.ProductService;
import com.project.mobile_phone_shop.Mapper.Mapper;
import com.project.mobile_phone_shop.Repository.ProductRepository;
import com.project.mobile_phone_shop.Validate.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class productServiceImp implements ProductService {
    private final ProductRepository productRepository;
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
}
