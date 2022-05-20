package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codRecall"})
public class Recall implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codRecall;

	private String nome;

	private Date dataCadastro;

	private String chassiIni;

	private String chassiFim;

	private Integer anoModelo;

	private Date dataIni;

	private Date dataFim;
	
	@ManyToOne
	@JoinColumn(name="codFuncionario")
	private Funcionario funcionario;

}
