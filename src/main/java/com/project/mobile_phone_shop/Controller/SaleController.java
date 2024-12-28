package com.project.mobile_phone_shop.Controller;

import com.project.mobile_phone_shop.Dto.PriceDto;
import com.project.mobile_phone_shop.Dto.ProductDto;
import com.project.mobile_phone_shop.Dto.ProductImportDto;
import com.project.mobile_phone_shop.Dto.SaleDto;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Service.ProductService;
import com.project.mobile_phone_shop.Service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleController {
    private final ProductService productService;
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SaleDto saleDto) {
         saleService.sale(saleDto);
         return ResponseEntity.ok().build();
    }

}
