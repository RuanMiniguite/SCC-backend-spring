package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;

public class Moto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer codMoto;

	private String modelo;

	private String marca;

	private Integer anoFabricacao;

	private Integer anoModelo;

	private String cor;

	private String combustivel;

	private Double cc;

	private String chassi;

	private Double valor;

	private String placa;

	private Cliente cliente;

	private TipoMoto tipoMoto;

}
