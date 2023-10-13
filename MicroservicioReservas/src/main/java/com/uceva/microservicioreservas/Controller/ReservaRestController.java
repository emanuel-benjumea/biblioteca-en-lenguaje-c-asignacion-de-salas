package com.uceva.microservicioreservas.Controller;

import com.uceva.microservicioreservas.model.dao.ReservaRepository;
import com.uceva.microservicioreservas.model.entities.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/reservas")
public class ReservaRestController {
    private final ReservaRepository reservaRepository;

    @Autowired  //--error solucionado
    public ReservaRestController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        
        return reservaRepository.save(reserva);
    }

    @DeleteMapping("/{id}")
    public String eliminarReserva(@PathVariable Long id) {
        Optional<Reserva> reservaExistente = reservaRepository.findById(id);

        if (reservaExistente.isPresent()) {
            reservaRepository.deleteById(id);
            return "Reserva eliminada exitosamente.";
        } else {
            return "Reserva no encontrada.";
        }
    }
    
    @PutMapping("/{id}")
    public Reserva modificarReserva(@PathVariable Long id, @RequestBody Reserva nuevaReserva) {
        Optional<Reserva> reservaExistente = reservaRepository.findById(id);

        if (reservaExistente.isPresent()) {
            Reserva reservaActual = reservaExistente.get();
            reservaActual.setNombreSolicitante(nuevaReserva.getNombreSolicitante());
            reservaActual.setFecha(nuevaReserva.getFecha());
            reservaActual.setHora(nuevaReserva.getHora());


            return reservaRepository.save(reservaActual);
        } else {

            return null;
        }
    }


    @GetMapping("/historial/{userId}")//se agregan historial de reservas
    public List<Reserva> obtenerHistorialDeReservas(@PathVariable Long userId) {
        return reservaRepository.findByUserId(userId);
    }



    @GetMapping
    public List<Reserva> listarReservas() {
        return (List<Reserva>) reservaRepository.findAll();
    }


    @GetMapping("/consultar")
    public List<Reserva> consultarReservas(
            @RequestParam(name = "fechaInicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaInicio,
            @RequestParam(name = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaFin,
            @RequestParam(name = "estado", required = false) String estado) {

        if (fechaInicio != null && fechaFin != null && estado != null) {
            return reservaRepository.findByFechaBetweenAndEstado(fechaInicio, fechaFin, estado);
        } else if (fechaInicio != null && fechaFin != null) {
            return reservaRepository.findByFechaBetween(fechaInicio, fechaFin);
        } else if (estado != null) {
            return reservaRepository.findByEstado(estado);
        } else {
            return (List<Reserva>) reservaRepository.findAll();
        }
    }

}

