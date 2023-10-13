package com.uceva.microservicioreservas.model.dao;

import com.uceva.microservicioreservas.model.entities.Reserva;
import org.springframework.data.repository.CrudRepository;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {
}
