package br.com.flazeredo.servicos;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.com.flazeredo.entidades.Filme;
import br.com.flazeredo.entidades.Locacao;
import br.com.flazeredo.entidades.Usuario;
import br.com.flazeredo.exceptions.FilmeSemEstoqueException;
import br.com.flazeredo.exceptions.LocadoraException;
import br.com.flazeredo.utils.DataUtils;



public class LocacaoServiceTest {
	
	private LocacaoService service;
	
		
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		service = new LocacaoService();
	}
	
	
	
	@Test
	public void teste() throws Exception {
		//cenario		
		Usuario usuario = new Usuario("Usuario1");
		Filme filme = new Filme("Filme1", 2, 5.0);
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
			
		//verificacao 
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));		
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));
		
	}
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void testLocacaoFilmeSemEstoque() throws Exception{
		//cenario
		Usuario usuario = new Usuario("Usuario1");
		Filme filme = new Filme("Filme1", 0, 5.0);		
		
		//acao
		service.alugarFilme(usuario, filme);		
	}
	
	@Test
	public void testLocacaoUsuarioVazio() throws FilmeSemEstoqueException {
		//cenario
		Filme filme = new Filme("Filme 2", 1, 4.0);
				
		//acao
		try {
			service.alugarFilme(null, filme);
			Assert.fail();				
		}catch (LocadoraException e) {	
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
			
		}
		
	}
	
	@Test
	public void testLocacaoFilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Usuario");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
				
		service.alugarFilme(usuario, null);	
		
	}
		
	
}
