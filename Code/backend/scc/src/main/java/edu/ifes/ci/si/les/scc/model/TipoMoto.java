package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codTipo"})
public class TipoMoto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codTipo;
	
	@Column(length = 50)
    @NotBlank(message = "O tipo da moto deve ser preenchido")
    @Size(min = 2, max = 50, message = "Nome do tipo moto deve ter entre 2 e 50 letras")
	private String nome;

}
