package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameProduct;
    private Integer periodo;
    private String nameCliente;
    private LocalDate fechaRegistro;
    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "id_bodega")
    private BodegaEntity bodega;

}
