package com.project.mobile_phone_shop.Validate;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.Exception.AlreadyExistException;
import com.project.mobile_phone_shop.Exception.NotFoundException;
import com.project.mobile_phone_shop.Repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Validate {
    private final BrandRepository brandRepository;

    public void ValidateBrandIsExist(BrandDto dto) {
        Optional<Brand> existBrand = Optional.ofNullable(brandRepository.findByName(dto.getName()));
        if(existBrand.isPresent()){
            throw new AlreadyExistException();
        }
    }

    public void ValidateBrandNotFound(Integer id) {
        Optional<Brand> targetBrand = brandRepository.findById(id);
        if(targetBrand.isEmpty()) {
            throw new NotFoundException();
        }
    }
}
