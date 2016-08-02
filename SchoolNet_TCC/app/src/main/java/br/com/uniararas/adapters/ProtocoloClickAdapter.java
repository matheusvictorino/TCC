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
import br.com.uniararas.beans.ProtocoloClick;

/**
 * Created by theuv on 12/03/2016.
 */
public class ProtocoloClickAdapter extends BaseAdapter {
    private Context context_;
    private ArrayList<ProtocoloClick> items;

    public ProtocoloClickAdapter(Context context, ArrayList<ProtocoloClick> items) {
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
            convertView = LayoutInflater.from(this.context_).inflate(R.layout.list_item_protocolo_click, parent, false);
        }

        TextView protocolo = (TextView) convertView.findViewById(R.id.protocolo);
        protocolo.setText(items.get(position).getProtocolo());

        TextView data = (TextView) convertView.findViewById(R.id.data_protocolo);
        data.setText(items.get(position).getData());

        TextView status = (TextView) convertView.findViewById(R.id.status_protocolo);
        status.setText("Status: " + items.get(position).getStatus());

        return convertView;
    }
}
