package com.uceva.microservicioreservas.Controller;

import com.uceva.microservicioreservas.model.dao.ReservaRepository;
import com.uceva.microservicioreservas.model.entities.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaRestController {
    private final ReservaRepository reservaRepository;

    @Autowired  //--error solucionado
    public ReservaRestController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva){
        return reservaRepository.save(reserva);
    }
}

