package com.nvk.TrieuPhuMVP.Utilities;

public interface CheckExistForm {
    boolean existUserName(String username);
    boolean existEmail(String email);
    boolean checkRepass(String pass,String repass);
    void setErrorRepass();
}
