package br.com.uniararas.beans;

/**
 * Created by theuv on 12/03/2016.
 */
public class Horario implements Comparable<Horario> {
    private int index;
    private String inicio;
    private String termino;
    private String disciplina;
    private String horario;
    private String sala;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    @Override
    public int compareTo(Horario another) {
        if (another.getIndex() > this.getIndex()) {
            return 1;
        }

        if (another.getIndex() < this.getIndex()) {
            return -1;
        }

        return 0;
    }
}
