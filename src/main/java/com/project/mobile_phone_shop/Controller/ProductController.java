package com.project.mobile_phone_shop.Controller;

import com.project.mobile_phone_shop.Dto.PriceDto;
import com.project.mobile_phone_shop.Dto.ProductDto;
import com.project.mobile_phone_shop.Dto.ProductImportDto;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.ProductImportHistory;
import com.project.mobile_phone_shop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
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

    @PostMapping("/imports")
    public ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDto productDto) {
        productService.importProduct(productDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/salePrice/{productId}")
    public ResponseEntity<?> salePrice(@PathVariable Long productId, @RequestBody PriceDto priceDto) {
        productService.setSalePrice(productId,priceDto.getPrice());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/uploadProduct")
    public ResponseEntity<?> uploadProduct(@RequestParam("file") MultipartFile file) {
        Map<Integer,String> errorMap = productService.uploadProduct(file);
        return ResponseEntity.ok(errorMap);
    }




}
