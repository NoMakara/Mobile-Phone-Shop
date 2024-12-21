package com.project.mobile_phone_shop.ServiceImp;

import com.project.mobile_phone_shop.Dto.ModelDto;
import com.project.mobile_phone_shop.Dto.PageDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.Entity.Model;
import com.project.mobile_phone_shop.Filter.ModelFilter;
import com.project.mobile_phone_shop.Filter.ModelSpec;
import com.project.mobile_phone_shop.IService.ModelService;
import com.project.mobile_phone_shop.Mapper.Mapper;
import com.project.mobile_phone_shop.Repository.ModelRepository;
import com.project.mobile_phone_shop.Validate.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ModelServiceImp implements ModelService {
    private final ModelRepository modelRepository;
    private final Validate validate;
    private final Mapper mapper;

    @Override
    public Page<Model> getModel(Map<String, String> params) {
        ModelFilter filter = ModelFilter.buildModelFilter(params);
        Pageable pageable = PageDto.buildPageable(params);

        ModelSpec modelSpec = new ModelSpec(filter);

        return modelRepository.findAll(modelSpec,pageable);
    }

    @Override
    public ModelDto addModel(ModelDto dto) {
        Brand brand = validate.ValidateBrandNotFound(dto.getBrandId());
        validate.ValidateModelIsExist(dto);
        Model model = mapper.map(dto, Model.class);
        model.setBrand(brand);
        return mapper.map(modelRepository.save(model), ModelDto.class);
    }

    @Override
    public ModelDto getModelById(Integer id) {
        Model model = validate.ValidateModelNotFound(id);
        return mapper.map(model, ModelDto.class);
    }

    @Override
    public ModelDto updateModel(Integer id, ModelDto dto) {
        validate.ValidateModelNotFound(id);
        Model updatedModel = modelRepository.save(mapper.map(dto,Model.class));
        return mapper.map(updatedModel, ModelDto.class);
    }

    @Override
    public void deleteModel(Integer id) {
        validate.ValidateBrandNotFound(id);
        modelRepository.deleteById(id);
    }
}
