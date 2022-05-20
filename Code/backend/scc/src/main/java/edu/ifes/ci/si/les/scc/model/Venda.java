package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;

public class Venda implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer codVenda;

	private Date data;

	private Double valor;

	private Boolean pago;

	private Double desconto;

	private Cliente cliente;

	private Funcionario funcionario;

	private Moto moto;

}
