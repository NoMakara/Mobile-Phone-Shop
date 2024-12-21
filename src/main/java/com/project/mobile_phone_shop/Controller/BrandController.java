package com.project.mobile_phone_shop.Controller;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Dto.PageDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.IService.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<?> getBrand(@RequestParam Map<String,String> params) {
        Page<Brand> page = brandService.getBrand(params);
        PageDto pageDto = new PageDto(page);
        return ResponseEntity.ok(pageDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Integer id) {
        BrandDto brand = brandService.getBrandById(id);
        return ResponseEntity.ok(brand.getName());
    }

    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody BrandDto brand) {
        BrandDto createBrand = brandService.addBrand(brand);
        return ResponseEntity.ok(createBrand.getName());
    }

    @PutMapping
    public ResponseEntity<?> updateBrand(@RequestParam Integer id,
                                         @RequestBody BrandDto dto) {
        BrandDto updatedBrand = brandService.updateBrand(id, dto);
        return ResponseEntity.ok(updatedBrand.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
