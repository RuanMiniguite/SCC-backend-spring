//Autor
//------------------------------
//Patricia Regina Daros
//20181si004
//------------------------------

package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;


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
	
	@Column(length = 50)
	@NotNull(message = "Nome do recall deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome do produto deve ter entre 2 e 50 letras")
	private String nome;
	
	@NotNull(message = "Data de cadastro do recall ser preenchida")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataCadastro;
	
	@Column(length = 50)
	@NotNull(message = "O chassi inicial deve ser preenchido")
	@Size(min = 2, max = 50, message = "O chassi inicial deve ter entre 2 e 50 letras e numeros")
	private String chassiIni;

	@Column(length = 50)
	@NotNull(message = "O chassi final deve ser preenchido")
	@Size(min = 2, max = 50, message = "O chassi final deve ter entre 2 e 50 letras e numeros")
	private String chassiFim;
	
	@Digits(integer=4, fraction=0, message = "O ano do modelo deve ser preenchido com um valor inteiro")
	private Integer anoModelo;
	
	@NotNull(message = "Data inicial ser preenchida")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataIni;
	
	@NotNull(message = "Data final ser preenchida")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataFim;
	
	@ManyToOne
	@JoinColumn(name="codFuncionario")
	private Funcionario funcionario;

}