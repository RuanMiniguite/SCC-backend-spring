package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codRevisao"})
public class Revisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codRevisao;

	private Date data;

	private Double valor;
	
	@ManyToOne
	@JoinColumn(name="codFuncionario")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name="codMoto")
	private Moto moto;

}
