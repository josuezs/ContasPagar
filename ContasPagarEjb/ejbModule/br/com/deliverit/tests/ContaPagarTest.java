package br.com.deliverit.tests;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import br.com.deliverit.facade.ContasFacadeImpl;
import br.com.deliverit.model.Contas;

/**
 * Classe para validar as regras para inclusão de contas a pagar.
 * 
 * @author Josué
 */
@Testable
public class ContaPagarTest {

	private ContasFacadeImpl contasFacade = new ContasFacadeImpl();

	@Test
	@DisplayName("Teste completaço!")
	public void testeFull() {
		testaNenhumAtraso();
		testAtraso3Dias();
		testAtraso4Ou5Dias();
		testAtraso6Dias();
	}

	@Test
	@DisplayName("Nenhum dia em atraso")
	public void testaNenhumAtraso() {
		Contas conta = new Contas();
		conta.setNome("Teste");
		conta.setVlrOriginal(new BigDecimal("100"));
		conta.setDtaVencimento(LocalDate.parse("2020-09-08"));
		conta.setDtaPagamento(LocalDate.parse("2020-09-07"));
		contasFacade.processarPgto(conta);

		assertAll("Teste sem atraso.", () -> assertEquals(0, conta.getNumDiasAtraso()),
				() -> assertThat(conta.getVlrOriginal(), Matchers.comparesEqualTo(conta.getVlrCorrigido())),
				() -> assertThat(BigDecimal.ZERO, Matchers.comparesEqualTo(conta.getPerMulta())),
				() -> assertThat(BigDecimal.ZERO, Matchers.comparesEqualTo(conta.getPerJurosDia())));
	}

	@Test
	@DisplayName("Atraso: até 3 dias")
	public void testAtraso3Dias() {
		Contas conta = new Contas();
		conta.setNome("Teste");
		conta.setVlrOriginal(new BigDecimal("100"));
		conta.setDtaVencimento(LocalDate.parse("2020-09-06"));
		conta.setDtaPagamento(LocalDate.parse("2020-09-07"));
		contasFacade.processarPgto(conta);

		assertAll("Teste até 3 dias atraso.", () -> assertEquals(1, conta.getNumDiasAtraso()),
				() -> assertThat(new BigDecimal("2"), Matchers.comparesEqualTo(conta.getPerMulta())),
				() -> assertThat(new BigDecimal("0.1"), Matchers.comparesEqualTo(conta.getPerJurosDia())),
				() -> assertThat(new BigDecimal("102.1"), Matchers.comparesEqualTo(conta.getVlrCorrigido())));
	}

	@Test
	@DisplayName("Atraso: 4 ou 5 dias")
	public void testAtraso4Ou5Dias() {
		Contas conta = new Contas();
		conta.setNome("Teste");
		conta.setVlrOriginal(new BigDecimal("100"));
		conta.setDtaVencimento(LocalDate.parse("2020-09-03"));
		conta.setDtaPagamento(LocalDate.parse("2020-09-07"));
		contasFacade.processarPgto(conta);

		assertAll("Teste 4 ou 5 dias atraso.", () -> assertEquals(4, conta.getNumDiasAtraso()),
				() -> assertThat(new BigDecimal("3"), Matchers.comparesEqualTo(conta.getPerMulta())),
				() -> assertThat(new BigDecimal("0.2"), Matchers.comparesEqualTo(conta.getPerJurosDia())),
				() -> assertThat(new BigDecimal("103.8"), Matchers.comparesEqualTo(conta.getVlrCorrigido())));
	}

	@Test
	@DisplayName("Atraso: a partir de 6 dias")
	public void testAtraso6Dias() {
		Contas conta = new Contas();
		conta.setNome("Teste");
		conta.setVlrOriginal(new BigDecimal("100"));
		conta.setDtaVencimento(LocalDate.parse("2020-09-01"));
		conta.setDtaPagamento(LocalDate.parse("2020-09-07"));
		contasFacade.processarPgto(conta);

		assertAll("Teste > 6 dias atraso.", () -> assertEquals(6, conta.getNumDiasAtraso()),
				() -> assertThat(new BigDecimal("5"), Matchers.comparesEqualTo(conta.getPerMulta())),
				() -> assertThat(new BigDecimal("0.3"), Matchers.comparesEqualTo(conta.getPerJurosDia())),
				() -> assertThat(new BigDecimal("106.8"), Matchers.comparesEqualTo(conta.getVlrCorrigido())));
	}

}
