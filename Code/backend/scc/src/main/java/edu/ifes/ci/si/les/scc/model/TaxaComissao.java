//Author
// Natalia Pitanga

package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codTaxaComissao"})
public class TaxaComissao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codTaxaComissao;
	
	@NotNull(message = "A taxa da comissão deve ser preenchido")
    @Digits(integer=3, fraction=2, message = "A taxa da comissão e deve ser preenchido com dígitos")
	private Double taxa;
	
	@NotNull(message = "Valor limite deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "Valor limite deve ser preenchido com dígitos")
	private Double valorLimite;
	
	@Digits(integer=1, fraction=0, message = "O Tempo de casa deve ser preenchido com um valor inteiro")
	private Integer anoCasa;
	
	@Column(length = 25)
	@NotNull(message = "O cargo do funcionario deve ser preenchido")
	@Size(min = 2, max = 25, message = "O cargo do funcionario deve ter entre 2 e 25 letras")
	private String cargo;

}
