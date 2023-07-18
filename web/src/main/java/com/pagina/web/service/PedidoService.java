package com.pagina.web.service;

import com.pagina.web.model.Fruta;
import com.pagina.web.repository.FrutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final FrutaRepository frutaRepository;

    @Autowired
    public PedidoService(FrutaRepository frutaRepository) {
        this.frutaRepository = frutaRepository;
    }

    public double calcularValorTotalPedido(List<Fruta> frutas) {
        double valorTotal = 0.0;

        for (Fruta fruta : frutas) {
            // Obtener los datos de la fruta desde el repositorio
            Fruta frutaDb = frutaRepository.findByTipo(fruta.getTipo());

            if (frutaDb != null) {
                int cantidadPedido = fruta.getCantidad();
                int cantidadDisponible = frutaDb.getCantidad();
                double precioFruta = frutaDb.getPrecio();

                if (cantidadPedido <= cantidadDisponible) {
                    // Verificar las reglas para aplicar descuentos
                    if (frutas.size() > 5) {
                        // Aplicar descuento del 10% en el valor total
                        valorTotal += (precioFruta * cantidadPedido) * 0.9;
                    } else {
                        valorTotal += precioFruta * cantidadPedido;
                    }

                    // Actualizar la cantidad de la fruta disponible
                    frutaDb.setCantidad(cantidadDisponible - cantidadPedido);
                    frutaRepository.save(frutaDb);
                } else {
                    // La cantidad solicitada es mayor que la disponible
                    // Manejar la lógica de error o alerta correspondiente
                }
            } else {
                // La fruta solicitada no se encuentra en la base de datos
                // Manejar la lógica de error o alerta correspondiente
            }
        }

        return valorTotal;
    }
}
