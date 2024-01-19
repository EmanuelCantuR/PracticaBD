package com.example.demo.service;
import com.example.demo.entity.BodegaEntity;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.repository.BodegaRepository;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

public class BodegaService {

    @Autowired
    BodegaRepository bodegaRepository;
    @Autowired
    ProductoRepository productoRepository;

    BodegaEntity bodegaEntity;

    public Map<String, Object> DatosGenerales(Long idB, Long idP){
        Map<String , Object> dato = new HashMap<>();
        Optional<BodegaEntity> bodegaId=bodegaRepository.findById(idB);
        System.out.println(bodegaId);
        System.out.println("Datos de bodega");
        Optional<ProductoEntity> productoId = productoRepository.findById(idP);
        System.out.println(productoId);
        System.out.println("Datos de Producto");
        dato.put("Nombre de Bodega", bodegaId.orElseThrow().getName());
        dato.put("Costo mensual", bodegaId.orElseThrow().getCosto()*(bodegaId.orElseThrow().getM2()*30));
        dato.put("Costo anual",bodegaId.orElseThrow().getCosto()*(bodegaId.orElseThrow().getM2()*365));
        dato.put("Nombre del producto", productoId.orElseThrow().getNameProduct());
        dato.put("Nombre del cliente", productoId.orElseThrow().getNameCliente());
        return dato;
    }

    public List<BodegaEntity> findAllStores(){
        return (List<BodegaEntity>) bodegaRepository.findAll();
    }

    public BodegaEntity crearStore(BodegaEntity store) {

        bodegaRepository.save(store);
        return store;
    }

    public Optional<BodegaEntity> findById(Long id){
        return bodegaRepository.findById(id);
    }

    public void deleted(Long id){
        bodegaRepository.deleteById(id);
    }

    public double getCostoById(Long bodegaId) {
        BodegaEntity bodegaEntity = bodegaRepository.findById(bodegaId).orElse(null);
        return (bodegaEntity != null) ? bodegaEntity.getCosto() : 0.0;
    }

}