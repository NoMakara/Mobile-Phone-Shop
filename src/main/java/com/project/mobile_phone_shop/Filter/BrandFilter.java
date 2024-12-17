package com.project.mobile_phone_shop.Filter;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BrandFilter {
    private Integer id;
    private String name;
}
