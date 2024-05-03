package br.com.softdesign.api.factory;

import br.com.softdesign.api.pojo.User;


public class UserDataFactory {
    public static User buscarUsuarioParaAutentificacao() {

        User primeiroUsuarioDaLista= new User();
        primeiroUsuarioDaLista.setUsername("atuny0");
        primeiroUsuarioDaLista.setPassword("9uQFF1Lh");
        return primeiroUsuarioDaLista;
    }

    public static User buscarUsuarioPassandoUsernameVazio() {

        User usuarioVazio = new User();
        usuarioVazio.setUsername("");
        usuarioVazio.setPassword("9uQFF1Lh");
        return usuarioVazio;
    }

    public static User buscarUsuarioPassandoPasswordVazio() {

        User senhaVazio = new User();
        senhaVazio.setUsername("kdulyt");
        senhaVazio.setPassword("");
        return senhaVazio;
    }

    public static User senhaUsuarioUsandoCredenciaisDeOutros() {

        User senhaDeUsuarioDiferente = new User();
        senhaDeUsuarioDiferente.setUsername("kdulyt");
        senhaDeUsuarioDiferente.setPassword("ePawWgrnZR8L");
        return senhaDeUsuarioDiferente;
    }
}
