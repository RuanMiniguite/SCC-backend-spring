package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class itemRevisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
    @EmbeddedId
	private itemRevisaoPK id = new itemRevisaoPK();
	
	@Column(length = 50)
    @NotBlank(message = "Nome do Produto deve ser preenchido")
    @Size(min = 2, max = 50, message = "Nome do Produto deve ter entre 2 e 50 letras")
	private String nome;

	@NotBlank(message = "Valor Unitario deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "Valor Unitario deve ser preenchido com dígitos")
	private Double valorUnitario;
	
	@Digits(integer=1, fraction=0, message = "A quantidade do Produto deve ser preenchido com um valor inteiro")
	private Integer qtd;

}
