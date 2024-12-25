package com.project.mobile_phone_shop.Controller;

import com.project.mobile_phone_shop.Dto.ModelDto;
import com.project.mobile_phone_shop.Dto.PageDto;
import com.project.mobile_phone_shop.Entity.Model;
import com.project.mobile_phone_shop.IService.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @GetMapping
    public ResponseEntity<?> getModel(@RequestParam Map<String,String> params) {
        Page<Model> page = modelService.getModel(params);
        PageDto pageDto = new PageDto(page);
        return ResponseEntity.ok(pageDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getModelById(@PathVariable Long id) {
        ModelDto dto = modelService.getModelById(id);
        return ResponseEntity.ok(dto.getName());
    }

    @PostMapping
    public ResponseEntity<?> addModel(@RequestBody ModelDto dto) {
         ModelDto modelDto = modelService.addModel(dto);
         return ResponseEntity.ok(modelDto);
    }

    @PutMapping
    public ResponseEntity<?> updateModel(@RequestParam Long id, @RequestBody ModelDto name) {
        ModelDto modelDto = modelService.updateModel(id, name);
        return ResponseEntity.ok(modelDto.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }


}
