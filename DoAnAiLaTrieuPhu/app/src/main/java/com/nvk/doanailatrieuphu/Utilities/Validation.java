package com.nvk.doanailatrieuphu.Utilities;

public class Validation {

    private static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";

    public static boolean checkEmail(String emailInput){
        return (emailPattern.matches(emailInput) && emailInput.length() > 0);
    }

}
