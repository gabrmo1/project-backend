package br.com.project.util;

public class Validator {

    public static boolean stringIsValid(String value) {
        return value != null && !value.trim().isEmpty();
    }

}
