package com.uceva.microservicioreservas.model.dao;

import com.uceva.microservicioreservas.model.entities.Reserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {

    List<Reserva>findByUserId(long userId);
    List<Reserva> findByFechaBetweenAndEstado(String fechaInicio, String fechaFin, String estado);
    List<Reserva> findByFechaBetween(String fechaInicio, String fechaFin);
    List<Reserva> findByEstado(String estado);

}



