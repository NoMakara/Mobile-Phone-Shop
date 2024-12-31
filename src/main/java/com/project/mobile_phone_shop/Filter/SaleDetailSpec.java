package com.project.mobile_phone_shop.Filter;

import com.project.mobile_phone_shop.Entity.Sale;
import com.project.mobile_phone_shop.Entity.SaleDetail;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Data
public class SaleDetailSpec implements Specification<SaleDetail> {
    private final SaleDetailFilter saleDetailFilter;

    @Override
    public Predicate toPredicate(Root<SaleDetail> saleDetail,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        Join<SaleDetail, Sale> sale = saleDetail.join("sale");
        if(Objects.nonNull(saleDetailFilter.getStartDate())){
            cb.greaterThanOrEqualTo(sale.get("soldDate"),saleDetailFilter.getStartDate());
        }
        if(Objects.nonNull(saleDetailFilter.getEndDate())){
                cb.lessThanOrEqualTo(sale.get("soldDate"),saleDetailFilter.getEndDate());
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
