package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;

public class ComissaoVenda implements Serializable{

	private Integer codComissaoVenda;

	private Date dataIni;

	private Date dataFim;

	private Double valorTotal;

	private Boolean pago;

	private TaxaComissao taxaComissao;

	private Venda venda;

}
