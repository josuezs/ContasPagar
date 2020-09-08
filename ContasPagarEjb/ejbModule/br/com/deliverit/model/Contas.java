package br.com.deliverit.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidade da tabela de contas.
 * 
 * @author Josué.
 */
@Entity
@Table(name = "contas")
@SequenceGenerator(name = "contasSeq", sequenceName = "contas_seq", allocationSize = 1)
public class Contas {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contasSeq")
	@Column(name = "id")
	private BigInteger id;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "vlr_original", nullable = false)
	private BigDecimal vlrOriginal;

	@Column(name = "vlr_corrigido", nullable = false)
	private BigDecimal vlrCorrigido;

	@Column(name = "dta_vencimento", nullable = false)
	private LocalDate dtaVencimento;

	@Column(name = "dta_pagamento", nullable = false)
	private LocalDate dtaPagamento;

	@Column(name = "num_dias_atraso", nullable = false)
	private Integer numDiasAtraso;

	@Column(name = "per_multa", nullable = false)
	private BigDecimal perMulta;

	@Column(name = "per_juros_dia", nullable = false)
	private BigDecimal perJurosDia;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

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

	public BigDecimal getVlrCorrigido() {
		return vlrCorrigido;
	}

	public void setVlrCorrigido(BigDecimal vlrCorrigido) {
		this.vlrCorrigido = vlrCorrigido;
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

	public Integer getNumDiasAtraso() {
		return numDiasAtraso;
	}

	public void setNumDiasAtraso(Integer numDiasAtraso) {
		this.numDiasAtraso = numDiasAtraso;
	}

	public BigDecimal getPerMulta() {
		return perMulta;
	}

	public void setPerMulta(BigDecimal perMulta) {
		this.perMulta = perMulta;
	}

	public BigDecimal getPerJurosDia() {
		return perJurosDia;
	}

	public void setPerJurosDia(BigDecimal perJurosDia) {
		this.perJurosDia = perJurosDia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtaPagamento == null) ? 0 : dtaPagamento.hashCode());
		result = prime * result + ((dtaVencimento == null) ? 0 : dtaVencimento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numDiasAtraso == null) ? 0 : numDiasAtraso.hashCode());
		result = prime * result + ((perJurosDia == null) ? 0 : perJurosDia.hashCode());
		result = prime * result + ((perMulta == null) ? 0 : perMulta.hashCode());
		result = prime * result + ((vlrCorrigido == null) ? 0 : vlrCorrigido.hashCode());
		result = prime * result + ((vlrOriginal == null) ? 0 : vlrOriginal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contas other = (Contas) obj;
		if (dtaPagamento == null) {
			if (other.dtaPagamento != null)
				return false;
		} else if (!dtaPagamento.equals(other.dtaPagamento))
			return false;
		if (dtaVencimento == null) {
			if (other.dtaVencimento != null)
				return false;
		} else if (!dtaVencimento.equals(other.dtaVencimento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numDiasAtraso == null) {
			if (other.numDiasAtraso != null)
				return false;
		} else if (!numDiasAtraso.equals(other.numDiasAtraso))
			return false;
		if (perJurosDia == null) {
			if (other.perJurosDia != null)
				return false;
		} else if (!perJurosDia.equals(other.perJurosDia))
			return false;
		if (perMulta == null) {
			if (other.perMulta != null)
				return false;
		} else if (!perMulta.equals(other.perMulta))
			return false;
		if (vlrCorrigido == null) {
			if (other.vlrCorrigido != null)
				return false;
		} else if (!vlrCorrigido.equals(other.vlrCorrigido))
			return false;
		if (vlrOriginal == null) {
			if (other.vlrOriginal != null)
				return false;
		} else if (!vlrOriginal.equals(other.vlrOriginal))
			return false;
		return true;
	}

}