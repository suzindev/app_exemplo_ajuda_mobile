package br.com.suzintech.exemploajuda.database.dao;

import android.content.ContentValues;
import android.content.Context;

import br.com.suzintech.exemploajuda.database.DBOpenHelper;
import br.com.suzintech.exemploajuda.database.model.UsuarioModel;

public class UsuarioDAO extends AbstractDAO {

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
}
