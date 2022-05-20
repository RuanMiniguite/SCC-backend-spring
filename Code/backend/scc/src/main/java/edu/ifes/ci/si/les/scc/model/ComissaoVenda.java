package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;
import lombok.*;
import javax.persistence.*;

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

	private Date dataIni;

	private Date dataFim;

	private Double valorTotal;

	private Boolean pago;

	private TaxaComissao taxaComissao;

	private Venda venda;

}
