package br.com.deliverit.resource;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaResource {

	private String nome;

	private BigDecimal vlrOriginal;

	private LocalDate dtaVencimento;

	private LocalDate dtaPagamento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getVlrOriginal() {
		return vlrOriginal;
	}

	public void setVlrOriginal(BigDecimal vlrOriginal) {
		this.vlrOriginal = vlrOriginal;
	}

	public LocalDate getDtaVencimento() {
		return dtaVencimento;
	}

	public void setDtaVencimento(LocalDate dtaVencimento) {
		this.dtaVencimento = dtaVencimento;
	}

	public LocalDate getDtaPagamento() {
		return dtaPagamento;
	}

	public void setDtaPagamento(LocalDate dtaPagamento) {
		this.dtaPagamento = dtaPagamento;
	}

}
