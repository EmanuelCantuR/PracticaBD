package com.example.demo.repository;
import com.example.demo.entity.ProductoEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<ProductoEntity, Long> {
}
