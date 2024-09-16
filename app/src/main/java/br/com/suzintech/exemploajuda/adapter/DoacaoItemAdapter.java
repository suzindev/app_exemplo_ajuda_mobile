package br.com.suzintech.exemploajuda.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.suzintech.exemploajuda.R;
import br.com.suzintech.exemploajuda.domain.DoacaoItemModel;

public class DoacaoItemAdapter extends BaseAdapter {

    private Activity activity;

    private ArrayList<DoacaoItemModel> items;

    public DoacaoItemAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListItems(ArrayList<DoacaoItemModel> lista) {
        this.items = lista;
    }

    @Override
    public int getCount() {
        return items != null && !items.isEmpty() ? items.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return items != null ? items.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.lista_item_doacao, null, false);
        }

        DoacaoItemModel doacaoItemModel = items.get(i);

        TextView textView = view.findViewById(R.id.lista_item_doacao_txtItem);
        textView.setText(doacaoItemModel.getTxtItem());

        ImageView imageView = view.findViewById(R.id.lista_item_doacao_imgItem);

        Picasso.get().load(doacaoItemModel.getImgItem()).into(imageView);

        return view;
    }
}
