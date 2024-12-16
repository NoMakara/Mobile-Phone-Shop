package com.project.mobile_phone_shop.Controller;

import com.project.mobile_phone_shop.Dto.BrandDto;
import com.project.mobile_phone_shop.Entity.Brand;
import com.project.mobile_phone_shop.ServiceImp.BrandServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BrandController {
    private final BrandServiceImp brandService;

    @GetMapping("/brands")
    public ResponseEntity<?> getAllBrand() {
        List<BrandDto> list = brandService.getAllBrand();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Integer id) {
        BrandDto brand = brandService.getBrandById(id);
        return ResponseEntity.ok(brand);
    }

    @PostMapping("/brands")
    public ResponseEntity<?> createBrand(@RequestBody BrandDto brand) {
        Brand createBrand = brandService.addBrand(brand);
        return ResponseEntity.ok(createBrand);
    }

    @PutMapping("/brands")
    public ResponseEntity<?> updateBrand(@RequestParam Integer id,
                                         @RequestBody BrandDto dto) {
        Brand updateBrand = brandService.updateBrand(id, dto);
        return ResponseEntity.ok(updateBrand);
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
