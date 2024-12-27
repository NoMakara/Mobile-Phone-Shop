package com.project.mobile_phone_shop.Validate;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Dto.ModelDto;
import com.project.mobile_phone_shop.Dto.ProductDto;
import com.project.mobile_phone_shop.Dto.ProductImportDto;
import com.project.mobile_phone_shop.Entity.*;
import com.project.mobile_phone_shop.Exception.AlreadyExistException;
import com.project.mobile_phone_shop.Exception.CannotBeNullException;
import com.project.mobile_phone_shop.Exception.NotFoundException;
import com.project.mobile_phone_shop.Repository.BrandRepository;
import com.project.mobile_phone_shop.Repository.ColorRepository;
import com.project.mobile_phone_shop.Repository.ModelRepository;
import com.project.mobile_phone_shop.Repository.ProductRepository;
import com.project.mobile_phone_shop.Response.ApiResponse;
import com.project.mobile_phone_shop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
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

//    public void ValidateProductIsExist(ProductDto dto) {
//        Optional<Product> product = Optional.ofNullable(productRepository.findByName(dto));
//        if(product.isPresent()){
//            throw new AlreadyExistException();
//        }
//    }

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

    public Product ValidateImportProduct(Product product, ProductImportDto importDto) {
        if(importDto.getImportUnit() == null){
            throw new CannotBeNullException();
        }
        Integer availableUnit = 0;
        if (product.getAvailableUnit() != null){
            availableUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(importDto.getImportUnit() + availableUnit);
        return product;
    }
}
