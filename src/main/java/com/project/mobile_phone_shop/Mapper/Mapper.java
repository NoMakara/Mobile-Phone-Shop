package com.project.mobile_phone_shop.Mapper;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Dto.ModelDto;
import com.project.mobile_phone_shop.Dto.ProductImportDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.Entity.Model;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.ProductImportHistory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Mapper {
    private final ModelMapper modelMapper;

    public ProductImportHistory mapToProductImportHistory(ProductImportDto dto, Product product) {
        if (dto == null ) {
            return null;
        }

        ProductImportHistory history = new ProductImportHistory();

        if ( history != null ) {
            history.setImportUnit(dto.getImportUnit());
            history.setPricePerUnit(dto.getImportPrice());
            history.setImportDate(dto.getImportDate());
        }
        history.setProduct(product);

        return history;
    }

    public <T, U> U map(T source, Class<U> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}
