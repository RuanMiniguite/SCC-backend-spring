package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50)
	@NotNull(message = "Nome do produto deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome do produto deve ter entre 2 e 50 letras")
	private String nome;
	
	@Column(length = 50)
	@NotNull(message = "Nome da marca deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome da marca deve ter entre 2 e 50 letras")
	private String marca;
	
	@NotNull(message = "Valor do produto deve ser preenchido")
	@Digits(integer=6, fraction=2, message = "Valor do produto deve ser preenchido com dígitos")
	private Double valor;
	
	@Digits(integer=2, fraction=0, message = "A quantidade do Produto deve ser preenchido com um valor inteiro")
	@Min(value = 0, message = "A quantidade em estoque deve ser maior que 0 (Zero)")
	private Integer qtd;

}
