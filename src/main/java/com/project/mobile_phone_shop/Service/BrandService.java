package com.project.mobile_phone_shop.Service;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Entity.Brand;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface BrandService {
    BrandDto getBrandById(Integer id);
    Page<Brand> getBrand(Map<String, String> params);
    BrandDto addBrand(BrandDto brand);
    BrandDto updateBrand(Integer id, BrandDto dto);
    void deleteBrand(Integer id);
    BrandDto findByName(String name);
}
