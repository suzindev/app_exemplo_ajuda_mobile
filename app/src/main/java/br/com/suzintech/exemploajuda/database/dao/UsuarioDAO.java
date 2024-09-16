package br.com.suzintech.exemploajuda.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.suzintech.exemploajuda.database.DBOpenHelper;
import br.com.suzintech.exemploajuda.database.model.UsuarioModel;

public class UsuarioDAO extends AbstractDAO {

    private String[] colunas = new String[]{
            UsuarioModel.COLUNA_ID,
            UsuarioModel.COLUNA_USUARIO,
            UsuarioModel.COLUNA_SENHA
    };

    public UsuarioDAO(Context context) {
        helper = new DBOpenHelper(context);
    }

    public long insert(final UsuarioModel usuarioModel) {
        long result = -1;

        try {
            Open();

            ContentValues values = new ContentValues();
            values.put(UsuarioModel.COLUNA_USUARIO, usuarioModel.getUsuario());
            values.put(UsuarioModel.COLUNA_SENHA, usuarioModel.getSenha());

            result = db.insert(UsuarioModel.TABLE_NAME, null, values);
        } finally {
            Close();
        }

        return result;
    }

    public boolean select(final UsuarioModel usuarioModel) {
        try {
            Open();

            Cursor cursor = db.query(
                    UsuarioModel.TABLE_NAME,
                    colunas,
                    UsuarioModel.COLUNA_USUARIO + " = ? AND " + UsuarioModel.COLUNA_SENHA + " = ?",
                    new String[]{usuarioModel.getUsuario(), usuarioModel.getSenha()},
                    null,
                    null,
                    null);

            cursor.moveToFirst();

            return cursor.getCount() > 0;
        } finally {
            Close();
        }
    }

    public ArrayList<UsuarioModel> selectAll() {
        ArrayList<UsuarioModel> listaUsuarioLocal;

        try {
            Open();

            listaUsuarioLocal = new ArrayList<>();

            Cursor cursor = db.query(
                    UsuarioModel.TABLE_NAME,
                    colunas,
                    null,
                    null,
                    null,
                    null,
                    null);

            cursor.moveToFirst();

            while (cursor.isAfterLast()) {
                UsuarioModel usuarioModel = new UsuarioModel();
                usuarioModel.setId(cursor.getInt(0));
                usuarioModel.setUsuario(cursor.getString(1));
                usuarioModel.setSenha(cursor.getString(2));

                listaUsuarioLocal.add(usuarioModel);

                cursor.moveToNext();
            }
        } finally {
            Close();
        }

        return listaUsuarioLocal;
    }
}
