package com.project.mobile_phone_shop.ServiceImp;

import com.project.mobile_phone_shop.Dto.ProductSoldDto;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.Sale;
import com.project.mobile_phone_shop.Entity.SaleDetail;
import com.project.mobile_phone_shop.Repository.SaleDetailRepository;
import com.project.mobile_phone_shop.Service.SaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SaleDetailServiceImpl implements SaleDetailService {
    private final SaleDetailRepository saleDetailRepository;

    @Override
    public void createSaleDetails(List<ProductSoldDto> products, Map<Long, Product> productMap, Sale sale) {
        products.forEach(p -> {
            Product product = productMap.get(p.getProductId());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(product.getSalePrice());
            saleDetail.setProduct(product);
            saleDetail.setSale(sale);
            saleDetail.setUnit(p.getNumberOfUnit());
            saleDetailRepository.save(saleDetail);
        });
    }
}
