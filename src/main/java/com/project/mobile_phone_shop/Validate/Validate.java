package com.project.mobile_phone_shop.Validate;

import com.project.mobile_phone_shop.Dto.*;
import com.project.mobile_phone_shop.Entity.*;
import com.project.mobile_phone_shop.Exception.AlreadyExistException;
import com.project.mobile_phone_shop.Exception.NotFoundException;
import com.project.mobile_phone_shop.Repository.BrandRepository;
import com.project.mobile_phone_shop.Repository.ColorRepository;
import com.project.mobile_phone_shop.Repository.ModelRepository;
import com.project.mobile_phone_shop.Repository.ProductRepository;
import com.project.mobile_phone_shop.Response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Validate {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;

    public void ValidateBrandIsExist(BrandDto dto) {
        Optional<Brand> existBrand = Optional.ofNullable(brandRepository.findByName(dto.getName()));
        if(existBrand.isPresent()){
            throw new AlreadyExistException();
        }
    }

    public Brand ValidateBrandNotFound(Integer id) {
        return brandRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void ValidateModelIsExist(ModelDto dto) {
        Optional<Model> model = Optional.ofNullable(modelRepository.findByName(dto.getName()));
        if(model.isPresent()){
            throw new AlreadyExistException();
        }
    }

    public Model ValidateModelNotFound(Long id) {
        return modelRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Product ValidateProductNotFound(Long id) {
        return productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    private Color ValidateColorNotFound(Long colorId) {
        return colorRepository.findById(colorId).orElseThrow(NotFoundException::new);
    }

    public Product setProduct(ProductDto productDto) {
        Model model = ValidateModelNotFound(productDto.getModelId());
        Color color = ValidateColorNotFound(productDto.getColorId());

        String productName = model.getName() + " " + color.getName();

        Product product = new Product();
        product.setModel(model);
        product.setColor(color);
        product.setName(productName);

        return product;
    }

    public Product ValidateImportProduct(Product product, Integer importUnit) {
        Integer availableUnit = 0;
        if (product.getAvailableUnit() != null){
            availableUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(importUnit + availableUnit);
        return product;
    }

    public void validateProductDuringSell(Map<Long,Product> productMap, List<ProductSoldDto> products){
        products.forEach(p -> {
            Product product = productMap.get(p.getProductId());
            if(product == null || product.getAvailableUnit() < p.getNumberOfUnit()) {
                throw new ApiResponse("Product [%s] Not enough products".formatted(product != null ? product.getName() : "Unknown"),HttpStatus.BAD_REQUEST);
            }
        });
    }
}
