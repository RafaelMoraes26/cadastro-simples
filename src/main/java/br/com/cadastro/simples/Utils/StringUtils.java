package br.com.cadastro.simples.Utils;

public class StringUtils {

    public StringUtils() {}

    public static boolean isAeValidString(String s) {
        return s != null && !s.isBlank() && !s.isEmpty();
    }
}
