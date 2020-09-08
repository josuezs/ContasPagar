package br.com.deliverit.facade;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Local;

import br.com.deliverit.exception.BusinessException;
import br.com.deliverit.model.Contas;

@Local
public interface ContasFacade {

	public void incluirConta(Contas conta) throws Exception;

	public Contas buscarContaPessoa(String nome, LocalDate dtaVencimento) throws BusinessException;

	public List<Contas> buscarContas();
	
}
