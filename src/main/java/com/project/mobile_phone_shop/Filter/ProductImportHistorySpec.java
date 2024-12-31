package com.project.mobile_phone_shop.Filter;

import com.project.mobile_phone_shop.Entity.Product;
import com.project.mobile_phone_shop.Entity.ProductImportHistory;
import com.project.mobile_phone_shop.Entity.Sale;
import com.project.mobile_phone_shop.Entity.SaleDetail;
import jakarta.persistence.criteria.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Data
public class ProductImportHistorySpec implements Specification<ProductImportHistory> {
    private final ProductImportHistoryFilter ImportHistoryFilter;

    @Override
    public Predicate toPredicate(Root<ProductImportHistory> importHistory,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(ImportHistoryFilter.getStartDate())){
            cb.greaterThanOrEqualTo(importHistory.get("importDate"),ImportHistoryFilter.getStartDate());
        }
        if(Objects.nonNull(ImportHistoryFilter.getEndDate())){
                cb.lessThanOrEqualTo(importHistory.get("importDate"),ImportHistoryFilter.getEndDate());
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
