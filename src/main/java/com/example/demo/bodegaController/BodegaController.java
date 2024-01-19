package com.example.demo.bodegaController;

import com.example.demo.entity.BodegaEntity;
import com.example.demo.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/bodega")

    public class BodegaController {

        @Autowired
        BodegaService bodegaService;

         @GetMapping("/dato/{idB}/{idP}")
         public Map<String, Object> datos(@PathVariable Long idB, @PathVariable Long idP){

             return bodegaService.DatosGenerales(idB, idP);

    }

    @GetMapping
    public ResponseEntity<List<BodegaEntity>> getAllStores(){
        List<BodegaEntity> data = bodegaService.findAllStores();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(data);
    }

    @PostMapping("/save")
    public ResponseEntity<BodegaEntity> postStore(@RequestBody BodegaEntity store){
        bodegaService.crearStore(store);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(store);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BodegaEntity>> getById(@PathVariable Long id){
        Optional<BodegaEntity> store= bodegaService.findById(id);
        return ResponseEntity.ok(store);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/{id}")
    public void deleteArticle(@PathVariable Long id){
        bodegaService.deleted(id);

    }

}
