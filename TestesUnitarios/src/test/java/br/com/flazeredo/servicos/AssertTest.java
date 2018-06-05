package br.com.flazeredo.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.com.flazeredo.entidades.Usuario;

public class AssertTest {
	
	@Test
	public void test() {
		Assert.assertFalse(false);
		Assert.assertTrue(true);
		
		Assert.assertEquals(1, 1);
		Assert.assertEquals(0.51234, 0.512, 0.001);
		Assert.assertEquals(Math.PI, 3,14);
		
		int i = 5;
		Integer i2 = 5;
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals("bola", "bola");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bo"));
		
		Usuario u1 = new Usuario("Usuario1");
		Usuario u2 = new Usuario("Usuario1");
		Usuario u3 = null;
		
		Assert.assertEquals(u1, u2);
		Assert.assertSame(u2, u2);
		Assert.assertNull(u3);
		
	}

}
