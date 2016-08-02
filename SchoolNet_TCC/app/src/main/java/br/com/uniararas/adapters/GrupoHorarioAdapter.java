package br.com.uniararas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.uniararas.actvity.R;
import br.com.uniararas.beans.GrupoHorario;
import br.com.uniararas.beans.Horario;

/**
 * Created by theuv on 12/03/2016.
 */
public class GrupoHorarioAdapter extends BaseAdapter {
    private Context context_;
    private ArrayList<GrupoHorario> items;

    public GrupoHorarioAdapter(Context context, ArrayList<GrupoHorario> items) {
        this.context_ = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context_).inflate(R.layout.list_item_horario_aula, parent, false);
        }

        TextView dia = (TextView) convertView.findViewById(R.id.dia);
        dia.setText(items.get(position).getDia());

        TextView horario = (TextView) convertView.findViewById(R.id.horario);
        TextView disciplina = (TextView) convertView.findViewById(R.id.disciplina);

        horario.setText("");
        disciplina.setText("");

        for (Horario hr : items.get(position).getHorarios()) {
            horario.append(hr.getInicio() + " - " + hr.getTermino() + "\n");
            disciplina.append(hr.getDisciplina()+ "\n");
        }

        return convertView;
    }
}
