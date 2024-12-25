package com.project.mobile_phone_shop.Controller;

import com.project.mobile_phone_shop.Dto.ModelDto;
import com.project.mobile_phone_shop.Dto.PageDto;
import com.project.mobile_phone_shop.Dto.ProductDto;
import com.project.mobile_phone_shop.Entity.Model;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.IService.ModelService;
import com.project.mobile_phone_shop.IService.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getByProductId(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
         Product product = productService.createProduct(productDto);
         return ResponseEntity.ok(product);
    }

}
