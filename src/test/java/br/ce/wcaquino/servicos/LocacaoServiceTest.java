package br.ce.wcaquino.servicos;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testeLocacao() throws Exception {
		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 5, 5.0);

		// acao

		Locacao locacao = service.alugarFilme(usuario, filme);

			error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
			error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
	}

	@Test(expected = Exception.class)
	public void testeLocacao_filmeSemEstoque() throws Exception {

		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		// acao
		service.alugarFilme(usuario, filme);
	}

	@Test
	public void testeLocacao_filmeSemEstoque_2() {

		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		// acao
		try {
			service.alugarFilme(usuario, filme);
			Assert.fail("Should has throw exception");
		} catch (Exception e) {
		Assert.assertThat(e.getMessage(), CoreMatchers.is("Movie without batch"));
		}
	}

	@Test
	public void testeLocacao_filmeSemEstoque_3() throws Exception {

		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		exception.expect(Exception.class);
		exception.expectMessage("Movie without batch");
		// acao
		service.alugarFilme(usuario, filme);
	}

}
