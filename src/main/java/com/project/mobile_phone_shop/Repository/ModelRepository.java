package com.project.mobile_phone_shop.Repository;

import com.project.mobile_phone_shop.Entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ModelRepository extends JpaRepository<Model,Integer>, JpaSpecificationExecutor<Model> {
    Model findByName(String name);
}
