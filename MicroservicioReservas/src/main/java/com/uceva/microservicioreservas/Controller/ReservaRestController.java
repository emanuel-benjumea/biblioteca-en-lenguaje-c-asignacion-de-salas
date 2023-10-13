package com.uceva.microservicioreservas.Controller;

import com.uceva.microservicioreservas.model.dao.ReservaRepository;
import com.uceva.microservicioreservas.model.entities.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/historial/{userId}")
    public List<Reserva> obtenerHistorialDeReservas(@PathVariable Long userId) {
        return reservaRepository.findByUserId(userId);
    }


    @GetMapping
    public List<Reserva> listarReservas() {
        return (List<Reserva>) reservaRepository.findAll();
    }


}

