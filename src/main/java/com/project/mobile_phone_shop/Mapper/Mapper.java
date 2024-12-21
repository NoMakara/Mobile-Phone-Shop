package com.project.mobile_phone_shop.Mapper;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Dto.ModelDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.Entity.Model;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Mapper {
    private final ModelMapper modelMapper;

    public <T, U> U map(T source, Class<U> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}
