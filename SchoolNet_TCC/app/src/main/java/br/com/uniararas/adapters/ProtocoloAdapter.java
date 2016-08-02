package br.com.uniararas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.uniararas.actvity.R;
import br.com.uniararas.beans.Protocolo;

/**
 * Created by theuv on 12/03/2016.
 */
public class ProtocoloAdapter extends BaseAdapter {
    private Context context_;
    private ArrayList<Protocolo> items;

    public ProtocoloAdapter(Context context, ArrayList<Protocolo> items) {
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
            convertView = LayoutInflater.from(this.context_).inflate(R.layout.list_item_protocolo, parent, false);
        }

        TextView pessoa = (TextView) convertView.findViewById(R.id.nome_pessoa_protocolo);
        pessoa.setText(items.get(position).getNome());

        TextView status = (TextView) convertView.findViewById(R.id.protocolo_status);
        status.setText(items.get(position).getStatus());

        TextView setor = (TextView) convertView.findViewById(R.id.protocolo_setor);
        setor.setText(items.get(position).getSetor());

        TextView data = (TextView) convertView.findViewById(R.id.protocolo_data);
        data.setText(items.get(position).getData());

        TextView resposta = (TextView) convertView.findViewById(R.id.protocolo_resposta);
        resposta.setText(items.get(position).getResposta());

        return convertView;
    }
}
