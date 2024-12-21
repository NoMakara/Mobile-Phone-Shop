package com.project.mobile_phone_shop.Filter;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class ModelFilter {
    private Integer id;
    private String name;

    public static ModelFilter buildModelFilter(Map<String, String> params) {
        ModelFilter modelFilter = new ModelFilter();

        if (params.containsKey("name")) {
            modelFilter.setName(params.get("name"));
        }

        if (params.containsKey("id")) {
            modelFilter.setId(Integer.parseInt(params.get("id")));
        }

        return modelFilter;
    }
}
