package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;

import javax.persistence.*;
import edu.ifes.ci.si.les.scc.model.enums.Pessoa;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codCliente"})
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codCliente;
	
	private String nome;

	private String doc;

	private String email;

	private String telefone;

	private String estado;

	private String cidade;

	private String cep;

	private String bairro;

	private Pessoa pessoa;

}
