package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;
import edu.ifes.ci.si.les.scc.model.enums.Admin;


public class Funcionario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer codFuncionario;

	private String name;

	private String cpf;

	private String telefone;

	private Date dataNascimento;

	private String estado;

	private String cep;

	private String cidade;

	private String bairro;

	private String login;

	private String senha;

	private String cargo;

	private Double salario;

	private Admin admin;

}
