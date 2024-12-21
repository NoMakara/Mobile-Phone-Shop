package com.project.mobile_phone_shop.Filter;

import com.project.mobile_phone_shop.Entity.Model;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ModelSpec implements Specification<Model> {
    private final ModelFilter modelFilter;

    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Model> model, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (!modelFilter.getName().isEmpty()) {
            Predicate name = cb.like(cb.upper(model.get("name")), "%" + modelFilter.getName().toUpperCase() + "%");
            predicates.add(name);
        }

        if (modelFilter.getId() != null) {
            Predicate id = model.get("id").in(modelFilter.getId());
            predicates.add(id);
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }

}
