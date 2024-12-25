package com.project.mobile_phone_shop.Filter;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class ModelFilter {
    private Long id;
    private String name;

    public static ModelFilter buildModelFilter(Map<String, String> params) {
        ModelFilter modelFilter = new ModelFilter();

        if (params.containsKey("model_name")) {
            modelFilter.setName(params.get("model_name"));
        }

        if (params.containsKey("id")) {
            modelFilter.setId(Long.parseLong(params.get("id")));
        }

        return modelFilter;
    }
}
