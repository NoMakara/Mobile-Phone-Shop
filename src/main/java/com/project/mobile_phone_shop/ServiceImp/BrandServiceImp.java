package com.project.mobile_phone_shop.ServiceImp;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.Exception.ProductNotFoundException;
import com.project.mobile_phone_shop.IService.IBrandService;
import com.project.mobile_phone_shop.Repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImp implements IBrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImp(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Brand getBrandById(Integer id) {
        return brandRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Integer id, Brand brand) {
            Optional<Brand> targetBrand = brandRepository.findById(id);
            if(targetBrand.isPresent()) {
                return brandRepository.save(brand);
            }
       throw new ProductNotFoundException();
    }

    @Override
    public Brand deleteBrand(Integer id) {
        Optional<Brand> targetBrand = brandRepository.findById(id);
        if(targetBrand.isPresent()){
            brandRepository.deleteById(id);
        }
        throw new ProductNotFoundException();
    }

    @Override
    public List<BrandDto> convertToDtos(List<Brand> brands){
        return brands.stream().map(this::convertToDto).toList();
    }

    @Override
    public BrandDto convertToDto(Brand brand){
        BrandDto brandDto = modelMapper.map(brand,BrandDto.class);
        return brandDto;
    }
}
