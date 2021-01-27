package com.cadastrocliente;

import com.cadastrocliente.util.Valida;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Exemplo de teste unitário local, que será executado na máquina de desenvolvimento (host).
 * Não depende do contexto do Android.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void CPFValido() {
        Valida valida = new Valida();
        boolean cpfValidado = valida.validaCPF("85202331550");
        assertTrue(cpfValidado);
    }

    @Test
    public void CPFInvalido() {
        Valida valida = new Valida();
        boolean cpfValidado = valida.validaCPF("1111111111");
        assertFalse(cpfValidado);
    }
}