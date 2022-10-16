/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milsondev.agendamento.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mein
 */
@Entity(name = "Agenda")
public class Agenda {

    
    Date dataAtual = new Date();

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull(message = "Data é obrigatória")
    private String dataDiaMesAno;

    @NotNull(message = "Hora de é obrigatória")
    private String timeHora;

    public Agenda(Long codigo, String dataDiaMesAno, String timeHora) {
        this.codigo = codigo;
        this.dataDiaMesAno = dataDiaMesAno;
        this.timeHora = timeHora;
    }

    public Agenda() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDataDiaMesAno() {
        
        DateFormat diaMesAno = new SimpleDateFormat("dd/MM/yyyy");
        dataDiaMesAno = diaMesAno.format(dataAtual);
        
        return dataDiaMesAno;
    }

    public void setDataDiaMesAno(String dataDiaMesAno) {
        this.dataDiaMesAno = dataDiaMesAno;
    }

    public String getTimeHora() {
        DateFormat hora = new SimpleDateFormat("HH:mm:ss");
        timeHora = hora.format(dataAtual);
        return timeHora;
    }

    public void setTimeHora(String timeHora) {
        this.timeHora = timeHora;
    }

}
