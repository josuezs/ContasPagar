package br.com.deliver.it.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.deliverit.facade.ContasFacade;
import br.com.deliverit.model.Contas;

@Named("contasMB")
@ViewScoped
public class ContasMB implements Serializable {

	@EJB
	private ContasFacade facade;

	private List<Contas> lstContas;

	@PostConstruct
	public void init() {
		lstContas = facade.buscarContas();
	}

	public List<Contas> getContas() {
		return lstContas;
	}

}