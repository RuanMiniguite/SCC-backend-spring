package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;

import edu.ifes.ci.si.les.scc.model.enums.Status;

public class RealizaRecall implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer codRealizarRecall;

	private Date data;

	private Status status;

	private Moto moto;

	private Recall recall;

}
