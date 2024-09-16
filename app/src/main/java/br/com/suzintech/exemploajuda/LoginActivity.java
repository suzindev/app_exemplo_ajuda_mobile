package br.com.suzintech.exemploajuda;

import android.content.DialogInterface;
import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    private EditText edUsuario;
    private EditText edSenha;

    private Button btnEntrar;
    private Button btnRegistrar;

    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsuario = findViewById(R.id.login_edUsuario);
        edSenha = findViewById(R.id.login_edSenha);

        btnEntrar = findViewById(R.id.login_btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edUsuario.getText().toString().isBlank()) {
                    mensagem("Campo usuário obrigatório!");
                } else if (edSenha.getText().toString().isBlank()) {
                    mensagem("Campo senha obrigatório!");
                } else {
                    UsuarioModel usuarioModel = new UsuarioModel();
                    usuarioModel.setUsuario(edUsuario.getText().toString());
                    usuarioModel.setSenha(edSenha.getText().toString());

                    usuarioDAO = new UsuarioDAO(LoginActivity.this);

                    if (usuarioDAO.select(usuarioModel)) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        alertDialog("Usuário ou senha inválidos!");
                    }
                }
            }
        });

        btnRegistrar = findViewById(R.id.login_btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it);
            }
        });
    }

    private void mensagem(String mensagem) {
        Toast.makeText(LoginActivity.this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private void alertDialog(final String mensagem) {
        AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
        alert.setTitle("Informação");
        alert.setIcon(ContextCompat.getDrawable(LoginActivity.this, R.drawable.img_login));
        alert.setMessage(mensagem);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        alert.create().show();
    }
}
