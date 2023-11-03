package com.uceva.microservicioreservas.model.entities;


import jakarta.persistence.*;
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
@Table(name="reserva")
public class Reserva {
    @Id
    private Long id;
    private String nombresolicitante;
    private String fecha;
    private String sala;
    private String hora;
    private String estado;

}
