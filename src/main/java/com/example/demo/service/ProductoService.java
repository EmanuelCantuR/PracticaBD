package com.example.demo.service;


import com.example.demo.entity.ProductoEntity;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service

public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;
    ProductoEntity productoEntity;

    BodegaService bodegaService;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, BodegaService bodegaService) {
        this.productoRepository = productoRepository;
        this.bodegaService = bodegaService;
    }

    public double getTotalAPagar(Long productoId) {
        ProductoEntity productoEntity = productoRepository.findById(productoId).orElse(null);

        if (productoEntity != null) {
            double costoBodega = bodegaService.getCostoById(productoEntity.getBodega().getId());
            return productoEntity.getPeriodo() * costoBodega;
        }

        return 0.0;
    }


    public List<ProductoEntity> findAllArticles(){
        return (List<ProductoEntity>) productoRepository.findAll();
    }

    public ProductoEntity crearStore(ProductoEntity article) {
//        BodegaEntity article = new BodegaEntity();
        article.setFechaRegistro(LocalDate.now());
        productoRepository.save(article);
        return article;
    }

    public Optional<ProductoEntity> findById(Long id){
        return productoRepository.findById(id);
    }

    public void deleted(Long id){
        productoRepository.deleteById(id);
    }
}
