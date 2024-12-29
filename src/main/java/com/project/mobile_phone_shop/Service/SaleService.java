package com.project.mobile_phone_shop.Service;

import com.project.mobile_phone_shop.Dto.SaleDto;
import com.project.mobile_phone_shop.Entity.Sale;

public interface SaleService {
    void sale(SaleDto saleDto);

    Sale getById(Long saleId);

    void cancelSale(Long saleId);
}
