package br.com.deliverit.facade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.deliverit.dao.ContasDAO;
import br.com.deliverit.exception.BusinessException;
import br.com.deliverit.model.Contas;

@Stateless
@LocalBean
public class ContasFacadeImpl implements ContasFacade {

	private static final BigDecimal CEM = new BigDecimal("100");

	@EJB
	private ContasDAO dao;

	@Override
	public void incluirConta(Contas conta) throws Exception {
		validarConta(conta);
		Contas contaDb = dao.buscarContaPessoa(conta.getNome(), conta.getDtaVencimento());
		if (contaDb != null)
			throw new BusinessException("Conta já cadastrada para esta pessoa.");

		processarPgto(conta);

		dao.save(conta);
	}

	public void processarPgto(Contas conta) {

		BigDecimal vlrCorrigido = conta.getVlrOriginal();
		Integer numDiasAtraso = 0;
		BigDecimal perJurosDia = BigDecimal.ZERO;
		BigDecimal perMulta = BigDecimal.ZERO;

		// Calcula juros apenas se existir atraso
		if (conta.getDtaPagamento().isAfter(conta.getDtaVencimento())) {

			// Caso optar por utilizar Java anterior à versão 8, utilizar uma lib terceira.
			numDiasAtraso = Period.between(conta.getDtaVencimento(), conta.getDtaPagamento()).getDays();

			if (numDiasAtraso > 5) {
				perJurosDia = new BigDecimal("0.3");
				perMulta = new BigDecimal("5");
			} else if (numDiasAtraso > 3) {
				perJurosDia = new BigDecimal("0.2");
				perMulta = new BigDecimal("3");
			} else {
				perJurosDia = new BigDecimal("0.1");
				perMulta = new BigDecimal("2");
			}
			BigDecimal vlrMulta = conta.getVlrOriginal().multiply(perMulta.divide(CEM));
			BigDecimal vlrJurosDiarios = conta.getVlrOriginal().multiply(perJurosDia.divide(CEM));
			vlrJurosDiarios = vlrJurosDiarios.multiply(new BigDecimal(numDiasAtraso));

			// Soma valores calculados para compor valor final corridigo.
			vlrCorrigido = vlrCorrigido.add(vlrMulta).add(vlrJurosDiarios);
		}
		conta.setNumDiasAtraso(numDiasAtraso);
		conta.setVlrCorrigido(vlrCorrigido);
		conta.setPerJurosDia(perJurosDia);
		conta.setPerMulta(perMulta);
	}

	private void validarConta(Contas conta) throws BusinessException {
		if (conta.getNome() == null || conta.getNome().trim().isEmpty())
			throw new BusinessException("Nome não informado.");
		if (conta.getDtaVencimento() == null)
			throw new BusinessException("Data de Vencimento não informada.");
		if (conta.getDtaPagamento() == null)
			throw new BusinessException("Data de Pagamento não informada.");
		if (conta.getVlrOriginal() == null || conta.getVlrOriginal().compareTo(BigDecimal.ZERO) <= 0)
			throw new BusinessException("Valor não informado ou negativo.");
	}

	@Override
	public Contas buscarContaPessoa(String nome, LocalDate dtaVencimento) throws BusinessException {
		if (nome == null || nome.trim().isEmpty())
			throw new BusinessException("Nome não informado.");
		if (dtaVencimento == null)
			throw new BusinessException("Data de Vencimento não informada.");
		return dao.buscarContaPessoa(nome, dtaVencimento);
	}

	@Override
	public List<Contas> buscarContas() {
		return dao.buscarContas();
	}

}
