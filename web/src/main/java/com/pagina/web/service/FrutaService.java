package com.pagina.web.service;

import com.pagina.web.model.Fruta;
import com.pagina.web.repository.FrutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrutaService {
    private final FrutaRepository frutaRepository;

    @Autowired
    public FrutaService(FrutaRepository frutaRepository) {
        this.frutaRepository = frutaRepository;
    }

    public List<Fruta> obtenerTodasLasFrutas() {
        return frutaRepository.findAll();
    }

    public Fruta obtenerFrutaPorId(Long id) {
        return frutaRepository.findById(id).orElse(null);
    }

    public Fruta crearFruta(Fruta fruta) {
        return frutaRepository.save(fruta);
    }

    public Fruta actualizarFruta(Fruta fruta) {
        return frutaRepository.save(fruta);
    }

    public void eliminarFruta(Long id) {
        frutaRepository.deleteById(id);
    }

    // Agrega métodos adicionales según tus necesidades
}
