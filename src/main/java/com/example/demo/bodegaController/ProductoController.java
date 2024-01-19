package com.example.demo.bodegaController;


import com.example.demo.entity.ProductoEntity;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/producto")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoEntity>> getAllArticle(){
        List<ProductoEntity> data = productoService.findAllArticles();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(data);
    }

//    @PostMapping("/calculate-total")
//    public double calculateTotalAPagar(@RequestParam Long productoId) {
//        return productoService.getTotalAPagar(productoId);
//    }

    @PostMapping("/save")
    public ResponseEntity<ProductoEntity> postStore(@RequestBody ProductoEntity article){
        productoService.crearStore(article);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(article);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductoEntity>> getById(@PathVariable Long id){
        Optional<ProductoEntity> store= productoService.findById(id);
        return ResponseEntity.ok(store);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/{id}")
    public void deleteArticle(@PathVariable Long id){
        productoService.deleted(id);

    }


}
