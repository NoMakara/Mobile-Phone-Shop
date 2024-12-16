package com.project.mobile_phone_shop.IService;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Entity.Brand;

import java.util.List;

public interface IBrandService {
    BrandDto getBrandById(Integer id);
    List<BrandDto> getAllBrand();
    Brand addBrand(BrandDto brand);
    Brand updateBrand(Integer id, BrandDto dto);
    void deleteBrand(Integer id);
    List<BrandDto> convertToDtos(List<Brand> brands);
    BrandDto convertToDto(Brand brand);
}
