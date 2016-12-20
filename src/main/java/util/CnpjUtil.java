package util;

import java.util.InputMismatchException;

import org.apache.commons.lang3.StringUtils;

class CnpjUtil {

    public static final int  DIGITOS_CNPJ       = 14;
    private static final int DOZE               = 12;
    private static final int ZERO_ASCII         = 48;
    private static final int DIGITOS_PRINCIPAIS = 11;
    private static final int PESO               = 2;

    private CnpjUtil() {
        super();
    }

    public static boolean cnpjValido(String cnpj) {
        boolean cnpjValido = false;
        if (!cnpfConsideradoInvalido(cnpj)) {
            char dig13, dig14;
            int sm, i, r, num, peso;

            try { // Calculo do 1º dígito verificador
                sm = 0;
                peso = PESO;
                for (i = DIGITOS_PRINCIPAIS; i >= 0; i--) {
                    // converte o i-ésimo caractere do CNPJ em um número
                    num = cnpj.charAt(i) - ZERO_ASCII;
                    sm = sm + (num * peso);
                    peso = peso + 1;
                    if (peso == 10) {
                        peso = PESO;
                    }
                }

                r = sm % DIGITOS_PRINCIPAIS;

                if ((r == 0) || (r == 1)) {
                    dig13 = '0';
                } else {
                    dig13 = (char) ((DIGITOS_PRINCIPAIS - r) + ZERO_ASCII);
                }
                // Calculo do 2º Digito Verificador
                sm = 0;
                peso = PESO;

                for (i = DOZE; i >= 0; i--) {
                    num = cnpj.charAt(i) - ZERO_ASCII;
                    sm = sm + (num * peso);
                    peso = peso + 1;
                    if (peso == 10) {
                        peso = PESO;
                    }
                }

                r = sm % DIGITOS_PRINCIPAIS;
                if ((r == 0) || (r == 1)) {
                    dig14 = '0';
                } else {
                    dig14 = (char) ((DIGITOS_PRINCIPAIS - r) + ZERO_ASCII);
                }
                // Verifica se os dígitos calculados conferem com os dígitos informados.
                cnpjValido = (dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13));
            } catch (InputMismatchException erro) {
                cnpjValido = false;
            }
        }
        return cnpjValido;
    }

    private static boolean cnpfConsideradoInvalido(String cpf) {
        for (int i = 0; i < 10; i++) {
            if (StringUtils.countMatches(cpf, String.valueOf(i)) == DIGITOS_CNPJ) {
                return true;
            }
        }
        return false;
    }
}
