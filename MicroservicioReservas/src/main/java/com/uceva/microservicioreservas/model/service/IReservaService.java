package com.uceva.microservicioreservas.model.service;

import com.uceva.microservicioreservas.model.entities.Reserva;

import java.util.List;

public interface IReservaService {
    List<Reserva> findAll();
    Reserva findById(Long id);
    Reserva save(Reserva reserva);
    void delete(Reserva reserva);
}