package com.project.mobile_phone_shop.Service;

import com.project.mobile_phone_shop.Dto.ProductSoldDto;
import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.Sale;
import com.project.mobile_phone_shop.Entity.SaleDetail;
import java.util.List;
import java.util.Map;

public interface SaleDetailService {
    void createSaleDetails(List<ProductSoldDto> products,
                                       Map<Long, Product> productMap,
                                       Sale sale);
}
