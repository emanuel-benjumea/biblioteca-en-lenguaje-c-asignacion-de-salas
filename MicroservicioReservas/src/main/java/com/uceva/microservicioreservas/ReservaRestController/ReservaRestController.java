package com.uceva.microservicioreservas.ReservaRestController;

import com.uceva.microservicioreservas.model.dao.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservaRestController {

   private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaRestController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }
@DeleteMapping("/reservas/{id}")
public void eliminarReserva(@PathVariable Long id) {
    reservaRepository.deleteById(id);    //crear reserva_repository
}
}

