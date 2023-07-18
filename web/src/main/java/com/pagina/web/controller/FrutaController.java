package com.pagina.web.controller;

import com.pagina.web.model.Fruta;
import com.pagina.web.repository.FrutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frutas")
public class FrutaController {
    private final FrutaRepository frutaRepository;

    @Autowired
    public FrutaController(FrutaRepository frutaRepository) {
        this.frutaRepository = frutaRepository;
    }

    @PostMapping
    public ResponseEntity<Fruta> crearFruta(@RequestBody Fruta fruta) {
        Fruta nuevaFruta = frutaRepository.save(fruta);
        return new ResponseEntity<>(nuevaFruta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Fruta>> obtenerFrutas() {
        List<Fruta> frutas = frutaRepository.findAll();
        return new ResponseEntity<>(frutas, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Fruta> obtenerFrutaPorId(@PathVariable Long id) {
        Fruta fruta = frutaRepository.findById(id).orElse(null);
        if (fruta != null) {
            return new ResponseEntity<>(fruta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fruta> actualizarFruta(@PathVariable Long id, @RequestBody Fruta frutaActualizada) {
        Fruta fruta = frutaRepository.findById(id).orElse(null);
        if (fruta != null) {
            fruta.setTipo(frutaActualizada.getTipo());
            fruta.setCantidad(frutaActualizada.getCantidad());
            fruta.setPrecio(frutaActualizada.getPrecio());
            fruta.setFechaActualizacion(frutaActualizada.getFechaActualizacion());
            Fruta frutaModificada = frutaRepository.save(fruta);
            return new ResponseEntity<>(frutaModificada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFruta(@PathVariable Long id) {
        frutaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
