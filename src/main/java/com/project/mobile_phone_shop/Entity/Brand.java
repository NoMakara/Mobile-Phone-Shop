package com.project.mobile_phone_shop.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Table(name = "tb_brands")
@NoArgsConstructor
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Integer id;

    @Column(name = "brand_name")
    private String name;

    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL)
    private List<Model> model;

    public Brand(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
