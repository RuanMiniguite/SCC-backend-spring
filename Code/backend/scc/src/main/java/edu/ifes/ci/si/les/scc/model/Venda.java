package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codVenda"})
public class Venda implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codVenda;

	private Date data;

	private Double valor;

	private Boolean pago;

	private Double desconto;
	
	@ManyToOne
	@JoinColumn(name="codCliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="codFuncionario")
	private Funcionario funcionario;
	
	@OneToOne
	@JoinColumn(name="codMoto")
	private Moto moto;

}
