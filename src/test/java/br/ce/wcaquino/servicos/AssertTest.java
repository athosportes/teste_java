package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void teste(){
        Assert.assertTrue("Erro de comparação", true);
        Assert.assertFalse(false);

        Assert.assertEquals(1, 1);
        Assert.assertEquals(0.51234, 0.512, 0.001);

        int i = 5;
        Integer i2 = 5;
        Assert.assertEquals(Integer.valueOf(i), i2);
        Assert.assertEquals(i, i2.intValue());

        Assert.assertEquals("bola", "bola");
        Assert.assertTrue("bola".equalsIgnoreCase("bola"));
        Assert.assertTrue("bola".startsWith("bo"));

        Usuario u1 = new Usuario("Usuario1");
        Usuario u2 = new Usuario("Usuario1");
        Usuario u3 = null;

        Assert.assertEquals(u1, u2);

        Assert.assertSame(u2, u2);

        Assert.assertNull(u3);




    }
}
