package br.com.softdesign.api.pojo;

// Usando Encapsulamento

public class User {
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
