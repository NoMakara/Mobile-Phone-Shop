package com.project.mobile_phone_shop.ServiceImp;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.Exception.NotFoundException;
import com.project.mobile_phone_shop.IService.IBrandService;
import com.project.mobile_phone_shop.Repository.BrandRepository;
import com.project.mobile_phone_shop.Validate.Validate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImp implements IBrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final Validate validate;

    @Override
    public BrandDto getBrandById(Integer id) {
        Brand brand = brandRepository.findById(id).orElseThrow(NotFoundException::new);
        BrandDto brandDto = convertToDto(brand);
        return brandDto;
    }

    @Override
    public List<BrandDto> getAllBrand() {
        List<Brand> brandList = brandRepository.findAll();
        List<BrandDto> brandDtos = convertToDtos(brandList);
        return brandDtos;
    }

    @Override
    public Brand addBrand(BrandDto dto) {
        validate.ValidateBrandIsExist(dto);
        Brand brand = new Brand(dto.getId(), dto.getName());
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Integer id, BrandDto dto) {
        validate.ValidateBrandNotFound(id);
        return brandRepository.save(new Brand(id,dto.getName()));
    }

    @Override
    public void deleteBrand(Integer id) {
        validate.ValidateBrandNotFound(id);
        brandRepository.deleteById(id);
    }

    @Override
    public List<BrandDto> convertToDtos(List<Brand> brands){
        return brands.stream().map(this::convertToDto).toList();
    }

    @Override
    public BrandDto convertToDto(Brand brand){
        return modelMapper.map(brand,BrandDto.class);
    }
}
