package br.com.suzintech.exemploajuda;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import br.com.suzintech.exemploajuda.database.dao.UsuarioDAO;
import br.com.suzintech.exemploajuda.database.model.UsuarioModel;

public class RegisterActivity extends AppCompatActivity {

    private EditText edUsuario;
    private EditText edSenha;
    private EditText edConfirmaSenha;

    private Button btnSalvar;

    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsuario = findViewById(R.id.register_edUsuario);
        edSenha = findViewById(R.id.register_edSenha);
        edConfirmaSenha = findViewById(R.id.register_edConfirmaSenha);

        btnSalvar = findViewById(R.id.register_btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edUsuario.getText().toString().isEmpty()) {
                    mensagem("Campo usuário é obrigatório!");
                } else if (edSenha.getText().toString().isEmpty()) {
                    mensagem("Campo senha é obrigatório!");
                } else if (edConfirmaSenha.getText().toString().isEmpty()) {
                    mensagem("Campo confirma senha é obrigatório!");
                } else if (!edSenha.getText().toString().equals(edConfirmaSenha.getText().toString())) {
                    mensagem("As senhas diferem!");
                } else {
                    usuarioDAO = new UsuarioDAO(RegisterActivity.this);

                    UsuarioModel usuarioModel = new UsuarioModel();
                    usuarioModel.setUsuario(edUsuario.getText().toString());
                    usuarioModel.setSenha(edSenha.getText().toString());

                    if (usuarioDAO.insert(usuarioModel) != -1) {
                        alertDialog("Usuário salvo!", true);
                    } else {
                        alertDialog("Falha ao salvar o usuário!", false);
                    }
                }
            }
        });
    }

    private void mensagem(String mensagem) {
        Toast.makeText(RegisterActivity.this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private void alertDialog(final String mensagem, final boolean fecharJanela) {
        AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
        alert.setTitle("Informação");
        alert.setIcon(ContextCompat.getDrawable(RegisterActivity.this, R.drawable.img_login));
        alert.setMessage(mensagem);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();

                if (fecharJanela)
                    finish();
            }
        });
        alert.create().show();
    }
}
