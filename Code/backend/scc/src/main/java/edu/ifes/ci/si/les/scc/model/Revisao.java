package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@NotBlank(message = "A data da Revisão deve ser preenchido")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	@NotBlank(message = "Valor da revisão deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "Valor da revisão deve ser preenchido com dígitos")
	private Double valor;
	
	@NotNull(message = "O Funcionario deve ser preenchido")
	@ManyToOne
	@JoinColumn(name="codFuncionario")
	private Funcionario funcionario;
	
	@NotNull(message = "A Moto deve ser preenchido")
	@ManyToOne
	@JoinColumn(name="codMoto")
	private Moto moto;

}
