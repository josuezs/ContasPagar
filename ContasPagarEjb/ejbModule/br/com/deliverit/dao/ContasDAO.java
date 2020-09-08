package br.com.deliverit.dao;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.deliverit.model.Contas;

@Stateless
@LocalBean
public class ContasDAO extends AbstractDAO {

	public Contas buscarContaPessoa(String nome, LocalDate dtaVencimento) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT e FROM Contas e \n");
		sb.append(" WHERE UPPER(nome) = :nome");
		sb.append("   AND dtaVencimento = :dtaVencimento");

		Query q = em.createQuery(sb.toString(), Contas.class);
		q.setParameter("nome", nome.toUpperCase());
		q.setParameter("dtaVencimento", dtaVencimento);
		return singleResult(q);
	}

	public List<Contas> buscarContas() {
		return findAll(Contas.class);
	}

}
