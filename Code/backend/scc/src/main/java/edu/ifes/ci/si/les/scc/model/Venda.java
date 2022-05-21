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
@EqualsAndHashCode(of = {"codVenda"})
public class Venda implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codVenda;
	
	@NotBlank(message = "Data da venda ser preenchida")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	@NotBlank(message = "O valor da venda deve ser preenchido")
	@Digits(integer=6, fraction=2, message = "O valor da venda deve ser preenchido com dígitos")
	private Double valor;

	private Boolean pago;
	
	@NotBlank(message = "O valor do desconto deve ser preenchido")
	@Digits(integer=6, fraction=2, message = "O valor do desconto deve ser preenchido com dígitos")
	private Double desconto;
	
	@NotNull(message = "O cliente deve ser preenchido")
	@ManyToOne
	@JoinColumn(name="codCliente")
	private Cliente cliente;
	
	@NotNull(message = "O funcionario deve ser preenchido")
	@ManyToOne
	@JoinColumn(name="codFuncionario")
	private Funcionario funcionario;
	
	@OneToOne
	@JoinColumn(name="codMoto")
	private Moto moto;

}