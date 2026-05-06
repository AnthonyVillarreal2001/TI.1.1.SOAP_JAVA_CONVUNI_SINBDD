package ec.edu.monster.controller;

public class AuthController {
    private static final String USER = "MONSTER";
    private static final String PASS = "MONSTER9";

    public boolean login(String u, String p) {
        if (u == null || p == null) return false;
        return USER.equalsIgnoreCase(u.trim()) && PASS.equals(p.trim());
    }
    public String getUsuario() { return USER; }
}
