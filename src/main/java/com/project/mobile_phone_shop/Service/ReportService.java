package com.project.mobile_phone_shop.Service;

import com.project.mobile_phone_shop.Dto.ProductReportDto;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<ProductReportDto> getProductReport(LocalDate startDate, LocalDate endDate);
}
