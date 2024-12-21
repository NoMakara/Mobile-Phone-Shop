package com.project.mobile_phone_shop.Validate;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Dto.ModelDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.Entity.Model;
import com.project.mobile_phone_shop.Exception.AlreadyExistException;
import com.project.mobile_phone_shop.Exception.NotFoundException;
import com.project.mobile_phone_shop.Repository.BrandRepository;
import com.project.mobile_phone_shop.Repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Validate {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

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

    public Model ValidateModelNotFound(Integer id) {
        return modelRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
