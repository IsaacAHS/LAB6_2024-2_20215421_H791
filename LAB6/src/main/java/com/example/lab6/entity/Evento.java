package com.example.lab6.entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="eventoId")
    private Integer id;

    @Column(nullable = true)
    private String nombre;

    @Column(name="asistentesEsperados",nullable = true)
    private Integer asisesper;

    @Column(nullable = true)
    private Date fecha;



}
