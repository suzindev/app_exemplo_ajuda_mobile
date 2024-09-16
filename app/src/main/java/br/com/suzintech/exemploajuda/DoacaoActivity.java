package br.com.suzintech.exemploajuda;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.suzintech.exemploajuda.adapter.DoacaoItemAdapter;
import br.com.suzintech.exemploajuda.domain.DoacaoItemModel;

public class DoacaoActivity extends AppCompatActivity {

    private ListView listView;

    private DoacaoItemAdapter doacaoItemAdapter;

    private ArrayList<DoacaoItemModel> listaDoacao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doacao);

        listView = findViewById(R.id.doacao_lista);

        listaDoacao = new ArrayList<>();

        DoacaoItemModel doacaoItemAgua = new DoacaoItemModel();
        doacaoItemAgua.setTxtItem("Agua");
        doacaoItemAgua.setImgItem("https://img.freepik.com/fotos-premium/garrafa-azul-de-agua-pura-com-gotas_159938-311.jpg");
        listaDoacao.add(doacaoItemAgua);

        DoacaoItemModel doacaoItemCobertor = new DoacaoItemModel();
        doacaoItemCobertor.setTxtItem("Cobertor");
        doacaoItemCobertor.setImgItem("https://e7.pngegg.com/pngimages/20/131/png-clipart-blue-blanket-woolen-blanket-autumn-and-winter.png");
        listaDoacao.add(doacaoItemCobertor);

        DoacaoItemModel doacaoItemRoupa = new DoacaoItemModel();
        doacaoItemRoupa.setTxtItem("Roupas");
        doacaoItemRoupa.setImgItem("https://w7.pngwing.com/pngs/987/597/png-transparent-assorted-color-clothes-on-clothes-rack-t-shirt-clothing-armoires-wardrobes-closet-clothes-hanger-clothing-retail-fashion-top.png");
        listaDoacao.add(doacaoItemRoupa);

        DoacaoItemModel doacaoItemAlimento = new DoacaoItemModel();
        doacaoItemAlimento.setTxtItem("Alimento");
        doacaoItemAlimento.setImgItem("https://w7.pngwing.com/pngs/32/657/png-transparent-leaf-vegetable-alimento-saludable-eating-food-drawing-health-natural-foods-leaf-vegetable-food.png");
        listaDoacao.add(doacaoItemAlimento);

        doacaoItemAdapter = new DoacaoItemAdapter(DoacaoActivity.this);
        doacaoItemAdapter.setListItems(listaDoacao);

        listView.setAdapter(doacaoItemAdapter);
        doacaoItemAdapter.notifyDataSetChanged();
    }
}
