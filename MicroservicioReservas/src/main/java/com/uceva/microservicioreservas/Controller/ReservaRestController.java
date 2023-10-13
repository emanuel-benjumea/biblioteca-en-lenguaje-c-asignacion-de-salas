package com.uceva.microservicioreservas.Controller;

import com.uceva.microservicioreservas.model.dao.ReservaRepository;
import com.uceva.microservicioreservas.model.entities.Reserva;
import com.uceva.microservicioreservas.model.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/reservas")
public class ReservaRestController {
    private final ReservaRepository reservaRepository;
    private final IReservaService reservaService;

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



    /* Aceptar o Denegar Reservas */
    @PostMapping("/aceptar/{reservaId}")
    public ResponseEntity<String> aceptarReserva(@PathVariable Long reservaId) {
        Reserva reserva = reservaService.findById(reservaId);

        if (reserva != null) {
            // Realiza acciones necesarias para aceptar la reserva (por ejemplo, cambiar el estado)
            reserva.setEstado("Aceptada");
            reservaService.save(reserva);  // Guarda la reserva actualizada en la base de datos
            return ResponseEntity.ok("Reserva aceptada correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada.");
        }
    }

    // Endpoint para denegar una reserva por su ID
    @PostMapping("/denegar/{reservaId}")
    public ResponseEntity<String> denegarReserva(@PathVariable Long reservaId) {
        Reserva reserva = reservaService.findById(reservaId);

        if (reserva != null) {
            // Realiza acciones necesarias para denegar la reserva (por ejemplo, cambiar el estado)
            reserva.setEstado("Denegada");
            reservaService.save(reserva);  // Guarda la reserva actualizada en la base de datos
            return ResponseEntity.ok("Reserva denegada correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada.");
        }
    }
}

