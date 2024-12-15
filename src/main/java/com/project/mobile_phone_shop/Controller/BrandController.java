package com.project.mobile_phone_shop.Controller;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.ServiceImp.BrandServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BrandController {
    private final BrandServiceImp brandService;

    public BrandController(BrandServiceImp brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public ResponseEntity<?> getAllBrand() {
        List<Brand> list = brandService.getAllBrand();
        List<BrandDto> brandDto = brandService.convertToDtos(list);
        return ResponseEntity.ok(brandDto);
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Integer id) {
        Brand brand = brandService.getBrandById(id);
        BrandDto brandDto = brandService.convertToDto(brand);
        return ResponseEntity.ok(brandDto);
    }

    @PostMapping("/brands")
    public ResponseEntity<?> createBrand(@RequestBody Brand brand) {
        Brand createBrand = brandService.createBrand(brand);
        BrandDto brandDto = brandService.convertToDto(createBrand);
        return ResponseEntity.ok(brandDto);
    }

    @PutMapping("/brands")
    public ResponseEntity<?> updateBrand(@RequestParam Integer id,
                                         @RequestBody Brand brand) {
        Brand updateBrand = brandService.updateBrand(id, brand);
        BrandDto brandDto = brandService.convertToDto(updateBrand);
        return ResponseEntity.ok(brandDto);
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
