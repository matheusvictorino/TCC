package br.com.uniararas.beans;

import java.util.ArrayList;

/**
 * Created by theuv on 12/03/2016.
 */
public class GrupoHorario implements Comparable<GrupoHorario> {
    private int index;
    private String dia;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

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

    @Override
    public int compareTo(GrupoHorario another) {
        if (another.getIndex() > this.getIndex()) {
            return -1;
        }

        if (another.getIndex() < this.getIndex()) {
            return 1;
        }

        return 0;
    }
}
