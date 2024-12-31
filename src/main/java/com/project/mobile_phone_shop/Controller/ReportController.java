package com.project.mobile_phone_shop.Controller;

import com.project.mobile_phone_shop.Dto.ExpenseReportDto;
import com.project.mobile_phone_shop.Dto.ProductReportDto;
import com.project.mobile_phone_shop.Dto.SaleDto;
import com.project.mobile_phone_shop.Service.ReportService;
import com.project.mobile_phone_shop.Service.SaleService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final SaleService saleService;
    private final ReportService reportService;

    //TODO fix runtime logic error after project end
    @GetMapping("/product/sold/{startDate}/{endDate}")
    public ResponseEntity<?> productSoldReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate) {
         List<ProductReportDto> productReport = reportService.getProductReport(startDate,endDate);
         return ResponseEntity.ok(productReport);
    }

    @GetMapping("/expense/{startDate}/{endDate}")
    public ResponseEntity<?> expenseReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate) {
        List<ExpenseReportDto> expenseReport = reportService.getExpenseReport(startDate,endDate);
        return ResponseEntity.ok(expenseReport);
    }


}
