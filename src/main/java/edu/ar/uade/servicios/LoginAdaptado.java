package edu.ar.uade.servicios;

public class LoginAdaptado implements ILoginAdapter{
    @Override
    public boolean autenticarse(String userName, String password) {
        return password.equals("123456");
    }
}
