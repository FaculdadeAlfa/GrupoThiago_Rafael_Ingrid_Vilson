package util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class CpfCnpjUtils {

    private final static String REGEX_REMOVER_MASCARA = "[./-]";

    private CpfCnpjUtils() {
        super();
    }

    public static boolean cpfOuCnpjValido(String cpfCnpj) {
        boolean valido = false;
        if (StringUtils.isNotBlank(cpfCnpj)) {
            String cpfCnpjSemFormatacao = cpfCnpj.replaceAll(REGEX_REMOVER_MASCARA, StringUtils.EMPTY);
            if (NumberUtils.isDigits(cpfCnpjSemFormatacao)) {
                int quantidadeCaracteres = cpfCnpjSemFormatacao.length();
                if (quantidadeCaracteres == CpfUtil.DIGITOS_CPF) {
                    valido = CpfUtil.cpfValido(cpfCnpjSemFormatacao);
                } else if (quantidadeCaracteres == CnpjUtil.DIGITOS_CNPJ) {
                    valido = CnpjUtil.cnpjValido(cpfCnpjSemFormatacao);
                }
            }
        }
        return valido;
    }
}
