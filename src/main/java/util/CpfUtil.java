package util;

import java.util.InputMismatchException;

import org.apache.commons.lang3.StringUtils;

class CpfUtil {

    public static final int  DIGITOS_CPF        = 11;
    private static final int SHIFT_CARACTERE    = 48;
    private static final int DIGITOS_VALIDOS    = 10;

    private CpfUtil() {
        super();
    }

    public static boolean cpfValido(String cpf) {
        boolean cpfValido = false;
        if (!cpfConsideradoInvalido(cpf)) {
            char dig10, dig11;
            int sm, i, r, num, peso;

            try {
                // Calculo do 1º dígito verificador
                sm = 0;
                peso = DIGITOS_VALIDOS;
                for (i = 0; i < 9; i++) {
                    // converte o i-ésimo caractere do CPF em um número
                    num = cpf.charAt(i) - SHIFT_CARACTERE;
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = DIGITOS_CPF - (sm % DIGITOS_CPF);
                if ((r == DIGITOS_VALIDOS) || (r == DIGITOS_CPF)) {
                    dig10 = '0';
                } else {
                    dig10 = (char) (r + SHIFT_CARACTERE); // converte no respectivo caractere numérico
                }

                // Calculo do 2º dígito verificador
                sm = 0;
                peso = DIGITOS_CPF;
                for (i = 0; i < DIGITOS_VALIDOS; i++) {
                    num = cpf.charAt(i) - SHIFT_CARACTERE;
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = DIGITOS_CPF - (sm % DIGITOS_CPF);
                if ((r == DIGITOS_VALIDOS) || (r == DIGITOS_CPF)) {
                    dig11 = '0';
                } else {
                    dig11 = (char) (r + SHIFT_CARACTERE);
                }

                // Verifica se os dígitos calculados conferem com os dígitos informados
                cpfValido = (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(DIGITOS_VALIDOS));
            } catch (InputMismatchException erro) {
                cpfValido = false;
            }
        }
        return cpfValido;
    }

    private static boolean cpfConsideradoInvalido(String cpf) {
        for (int i = 0; i < 10; i++) {
            if (StringUtils.countMatches(cpf, String.valueOf(i)) == DIGITOS_CPF) {
                return true;
            }
        }
        return false;
    }

}
