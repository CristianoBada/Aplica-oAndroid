package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Usuario;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioBD extends _Default {

    public boolean existeUsuario(String login, String pass) {
        try {
            BD bd = new BD();
            String comando = String.format("SELECT * FROM usuario WHERE login='%s' AND senha2='%s'", login, pass);
            ResultSet resultSet = bd.select(comando);
            if (resultSet != null) {
                ArrayList<Usuario> lista = new ArrayList<>();
                while (resultSet.next()) {
                    Usuario usuario = new Usuario();
                    lista.add(usuario);
                }
                if (lista.size() == 0) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }
        return false;
    }
}
