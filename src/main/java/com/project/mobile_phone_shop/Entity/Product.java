package com.project.mobile_phone_shop.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Table(name = "tb_products",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id", "color_id"})})
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", unique = true)
    private String name;

    @Column(name = "available_unit")
    private Integer availableUnit;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
}
