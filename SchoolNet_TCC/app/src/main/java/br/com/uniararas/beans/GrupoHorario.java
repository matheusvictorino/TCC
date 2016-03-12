package br.com.uniararas.beans;

import java.util.ArrayList;

/**
 * Created by theuv on 12/03/2016.
 */
public class GrupoHorario {
    private String dia;
    private ArrayList<Horario> horarios;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }
}
