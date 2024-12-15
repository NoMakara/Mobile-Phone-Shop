package com.project.mobile_phone_shop.ServiceUtil;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {
    @Bean
    public static ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
