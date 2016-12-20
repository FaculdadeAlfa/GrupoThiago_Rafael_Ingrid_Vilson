package util;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import junit.framework.TestCase;


public class CpfCnpjUtilsTest extends TestCase {

    private final static String BASE_CPF = "###########";
    private final static String BASE_CNPJ = "###########";
    private final static String[] ARRAY_CPF_VALIDO = { "22212666683", "97364662480", "61308279205", "46607952500", "16323448580" };
    private final static String[] ARRAY_CNPJ_VALIDO = { "10233466000190", "45866556000101", "75964932000153", "66224850000181", "52281442000157" };

    @Test
    public void testCpfCnpjNulo() {
        assertFalse(CpfCnpjUtils.cpfOuCnpjValido(null));
    }

    @Test
    public void testCpfCnpjVazio() {
        assertFalse(CpfCnpjUtils.cpfOuCnpjValido(""));
    }

    @Test
    public void testCpfCnpjComQuantidadeDeCaracteresInvalido() {
        assertFalse(CpfCnpjUtils.cpfOuCnpjValido("1"));
    }

    @Test
    public void testCpfComOnzeCaracteresAlfanumerico() {
        String cpfAlfanumerico = RandomStringUtils.randomAlphanumeric(CpfUtil.DIGITOS_CPF);
        assertFalse(CpfCnpjUtils.cpfOuCnpjValido(cpfAlfanumerico));
    }

    @Test
    public void testCpfComOnzeDigitosInvalidos() {
        assertFalse(CpfCnpjUtils.cpfOuCnpjValido("12345678901"));
    }

    @Test
    public void testCnpjComQuatorzeCaracteresAlfanumerico() {
        String cnpfAlfanumerico = RandomStringUtils.randomAlphanumeric(CnpjUtil.DIGITOS_CNPJ);
        assertFalse(CpfCnpjUtils.cpfOuCnpjValido(cnpfAlfanumerico));
    }

    @Test
    public void testCnpjComQuatorzeDigitosInvalidos() {
        assertFalse(CpfCnpjUtils.cpfOuCnpjValido("11223344556677"));
    }

    @Test
    public void testCpfValidos() {
        for (String cpf : ARRAY_CPF_VALIDO) {
            assertTrue(CpfCnpjUtils.cpfOuCnpjValido(cpf));
        }
    }

    @Test
    public void testCnpjValidos() {
        for (String cnpj : ARRAY_CNPJ_VALIDO) {
            assertTrue(CpfCnpjUtils.cpfOuCnpjValido(cnpj));
        }
    }

    @Test
    public void testCpfCnpjComDigitosConsideradosInvalidos() {
        for (int i = 0; i < 9; i++) {
            assertFalse(CpfCnpjUtils.cpfOuCnpjValido(BASE_CPF.replace("#", String.valueOf(i))));
            assertFalse(CpfCnpjUtils.cpfOuCnpjValido(BASE_CNPJ.replace("#", String.valueOf(i))));
        }
    }
}
