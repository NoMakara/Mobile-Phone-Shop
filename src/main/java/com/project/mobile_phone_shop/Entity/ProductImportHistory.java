package com.project.mobile_phone_shop.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "tb_product_import_history")
@NoArgsConstructor
@Entity
public class ProductImportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "import_id")
    private Long id;

    @Column(name = "import_date")
    private LocalDateTime importDate;

    @Column(name = "import_unit")
    private Integer importUnit;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
