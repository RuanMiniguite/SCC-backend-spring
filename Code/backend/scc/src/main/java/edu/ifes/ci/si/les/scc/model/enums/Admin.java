package edu.ifes.ci.si.les.scc.model.enums;

public enum Admin {

	FUNCIONARIO(0, "Funcionario"),
	GERENTE(1, "Gerente");
	
	private int cod;
	private String descricao;
	
	private Admin(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static Admin toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Admin x : Admin.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
