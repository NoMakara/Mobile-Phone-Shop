package com.project.mobile_phone_shop.ServiceImp;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.Filter.BrandFilter;
import com.project.mobile_phone_shop.Filter.BrandSpec;
import com.project.mobile_phone_shop.Service.BrandService;
import com.project.mobile_phone_shop.Mapper.Mapper;
import com.project.mobile_phone_shop.Repository.BrandRepository;
import com.project.mobile_phone_shop.Util.PageUtil;
import com.project.mobile_phone_shop.Validate.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BrandServiceImp implements BrandService {
    private final BrandRepository brandRepository;
    private final Mapper mapper;
    private final Validate validate;
    private final BrandFilter brandFilter;

    @Override
    public BrandDto getBrandById(Integer id) {
        Brand brand = validate.ValidateBrandNotFound(id);
        return mapper.map(brand,BrandDto.class);
    }

    @Override
    public Page<Brand> getBrand(Map<String, String> params) {

        if (params.containsKey("name")) {
            String name = params.get("name");
            brandFilter.setName(name);
        }

        if (params.containsKey("id")) {
            String id = params.get("id");
            brandFilter.setId(Integer.parseInt(id));
        }

        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if (params.containsKey(PageUtil.PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if (params.containsKey(PageUtil.PAGE_NUMBER)) {
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }

        BrandSpec brandSpec = new BrandSpec(brandFilter);

        Pageable pageable = PageUtil.getPageable(pageNumber,pageLimit);

        return brandRepository.findAll(brandSpec, pageable);
    }

    @Override
    public BrandDto addBrand(BrandDto dto) {
        validate.ValidateBrandIsExist(dto);
        Brand brand = mapper.map(dto,Brand.class);
        return mapper.map(brandRepository.save(brand),BrandDto.class);
    }

    @Override
    public BrandDto updateBrand(Integer id, BrandDto dto) {
        validate.ValidateBrandNotFound(id);
        Brand updatedBrand = brandRepository.save(mapper.map(dto,Brand.class));
        return mapper.map(updatedBrand, BrandDto.class);
    }

    @Override
    public void deleteBrand(Integer id) {
        validate.ValidateBrandNotFound(id);
        brandRepository.deleteById(id);
    }

    @Override
    public BrandDto findByName(String name) {
        return mapper.map(brandRepository.findByName(name), BrandDto.class);
    }

}
