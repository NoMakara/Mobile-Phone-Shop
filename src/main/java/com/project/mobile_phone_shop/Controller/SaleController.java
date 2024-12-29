package com.project.mobile_phone_shop.Controller;

import com.project.mobile_phone_shop.Dto.SaleDto;
import com.project.mobile_phone_shop.Service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SaleDto saleDto) {
         saleService.sale(saleDto);
         return ResponseEntity.ok().build();
    }

    @PutMapping("/cancel/{saleId}")
    public ResponseEntity<?> cancelSale(@PathVariable Long saleId) {
        saleService.cancelSale(saleId);
        return ResponseEntity.noContent().build();
    }

}
