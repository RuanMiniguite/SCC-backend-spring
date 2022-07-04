//Author
// Natalia Pitanga

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
@EqualsAndHashCode(of = {"codComissaoVenda"})
public class ComissaoVenda implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codComissaoVenda;
	
	@NotNull(message = "A data de Inicio do período do calculo da Comissão Venda deve ser preenchido")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataIni;
	
	@NotNull(message = "A data de Fim do período do calculo da Comissão Venda deve ser preenchido")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataFim;
	
	@NotNull(message = "Valor Total deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "Valor Total deve ser preenchido com dígitos")
	private Double valorTotal;
	
	private Boolean pago;
	
	@NotNull(message = "A taxa de comissão deve ser preenchido")
	@ManyToOne
	@JoinColumn(name="codTaxaComissao")
	private TaxaComissao taxaComissao;
	
	@OneToOne
	@JoinColumn(name="codVenda")
	private Venda venda;

}
