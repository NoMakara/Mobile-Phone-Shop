package com.project.mobile_phone_shop.ServiceImp;

import com.project.mobile_phone_shop.Dto.ProductReportDto;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.SaleDetail;
import com.project.mobile_phone_shop.Filter.SaleDetailFilter;
import com.project.mobile_phone_shop.Filter.SaleDetailSpec;
import com.project.mobile_phone_shop.Repository.ProductRepository;
import com.project.mobile_phone_shop.Repository.SaleDetailRepository;
import com.project.mobile_phone_shop.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReportServiceImp implements ReportService {
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ProductReportDto> getProductReport(LocalDate startDate, LocalDate endDate) {
        List<ProductReportDto> list = new ArrayList<>();

        SaleDetailFilter detailFilter = new SaleDetailFilter();
        Specification<SaleDetail> spec = new SaleDetailSpec(detailFilter);
        detailFilter.setStartDate(startDate);
        detailFilter.setEndDate(endDate);
        List<SaleDetail> saleDetails = saleDetailRepository.findAll(spec);

        List<Long> productId = saleDetails.stream().map(sd -> sd.getProduct().getId()).toList();

        Map<Long,Product> productMap = productRepository.findAllById(productId)
                .stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        Map<Product,List<SaleDetail>> saleDetailMap = saleDetails.stream()
                .collect(Collectors.groupingBy(SaleDetail::getProduct));

        for (var entry : saleDetailMap.entrySet()) {
            Product product = productMap.get(entry.getKey().getId());
            List<SaleDetail> sdList = entry.getValue();

            //get total unit
            Integer unit = sdList.stream()
                    .map(SaleDetail::getUnit)
                    .reduce(0,Integer::sum);

            //get total amount
            double totalAmount = sdList.stream()
                    .mapToDouble(sd -> sd.getUnit() * sd.getAmount().doubleValue())
                    .sum();

            ProductReportDto reportDto = new ProductReportDto();
            reportDto.setProductId(product.getId());
            reportDto.setProductName(product.getName());
            reportDto.setUnit(unit);
            reportDto.setTotalAmount(BigDecimal.valueOf(totalAmount));
            list.add(reportDto);
        }
        return list;
    }
}
