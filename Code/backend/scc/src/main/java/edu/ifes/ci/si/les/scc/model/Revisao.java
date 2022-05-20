package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;

public class Revisao implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer codRevisao;

	private Date data;

	private Double valor;

	private Funcionario funcionario;

	private Moto moto;

}
