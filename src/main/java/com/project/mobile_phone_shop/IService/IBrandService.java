package com.project.mobile_phone_shop.IService;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Entity.Brand;

import java.util.List;

public interface IBrandService {
    Brand getBrandById(Integer id);
    List<Brand> getAllBrand();
    Brand createBrand(Brand brand);
    Brand updateBrand(Integer id, Brand brand);
    Brand deleteBrand(Integer id);

    List<BrandDto> convertToDtos(List<Brand> brands);

    BrandDto convertToDto(Brand brand);
}
