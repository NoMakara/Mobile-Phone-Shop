package com.project.mobile_phone_shop.IService;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IBrandService {
    BrandDto getBrandById(Integer id);
    Page<Brand> getBrand(Map<String, String> params);
    BrandDto addBrand(BrandDto brand);
    BrandDto updateBrand(Integer id, BrandDto dto);
    void deleteBrand(Integer id);
    List<BrandDto> convertToDtos(List<Brand> brands);
    BrandDto convertToDto(Brand brand);
}
