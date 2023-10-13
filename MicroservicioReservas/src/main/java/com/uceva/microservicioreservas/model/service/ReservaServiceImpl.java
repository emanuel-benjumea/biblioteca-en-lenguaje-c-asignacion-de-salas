package com.uceva.microservicioreservas.model.service;

import com.uceva.microservicioreservas.model.dao.ReservaRepository;
import com.uceva.microservicioreservas.model.entities.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ReservaServiceImpl implements IReservaService{
    private ReservaRepository reservaDao;


    public ReservaServiceImpl(ReservaRepository reservaDao) {
        this.reservaDao = reservaDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reserva> findAll() {
        return (List<Reserva>) reservaDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Reserva findById(Long id) {
        return reservaDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Reserva save(Reserva reserva) {
        return reservaDao.save(reserva);
    }

    @Transactional
    @Override
    public void delete(Reserva reserva) {
        reservaDao.delete(reserva);
    }
}
