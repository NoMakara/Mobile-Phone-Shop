package com.project.mobile_phone_shop.Repository;

import com.project.mobile_phone_shop.Entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long>, JpaSpecificationExecutor<Model> {
    Model findByName(String name);
}
