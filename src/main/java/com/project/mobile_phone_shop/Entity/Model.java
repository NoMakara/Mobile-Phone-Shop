package com.project.mobile_phone_shop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.mobile_phone_shop.Dto.ModelDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_models")
@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;

}
