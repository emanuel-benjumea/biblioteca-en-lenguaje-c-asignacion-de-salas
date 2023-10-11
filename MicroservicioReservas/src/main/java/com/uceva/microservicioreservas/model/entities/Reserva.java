package com.uceva.microservicioreservas.model.entities;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//esto es parte deJPA que ES Java Persistence API
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    @Id
    private Long id;
    private String nombreSolicitante;
    private String fecha;
    private String sala;
    private String hora;

}
