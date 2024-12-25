package com.project.mobile_phone_shop.IService;

import com.project.mobile_phone_shop.Dto.ModelDto;
import com.project.mobile_phone_shop.Entity.Model;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ModelService {
    Page<Model> getModel(Map<String, String> params);

    ModelDto addModel(ModelDto dto);

    ModelDto getModelById(Long id);

    ModelDto updateModel(Long id, ModelDto dto);

    void deleteModel(Long id);
}
