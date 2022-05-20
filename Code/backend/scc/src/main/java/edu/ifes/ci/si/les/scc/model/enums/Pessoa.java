package edu.ifes.ci.si.les.scc.model.enums;

public enum Pessoa {

	FISICA(0, "Fisica"),
	JURIDICA(1, "Juridica");
	
	private int cod;
	private String descricao;
	
	private Pessoa(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static Pessoa toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Pessoa x : Pessoa.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
