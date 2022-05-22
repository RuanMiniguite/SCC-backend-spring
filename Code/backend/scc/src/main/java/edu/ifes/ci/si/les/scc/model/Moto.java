package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codMoto"})
public class Moto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codMoto;
	
	@Column(length = 50)
    @NotNull(message = "O modelo deve ser preenchido")
    @Size(min = 2, max = 50, message = "O modelo deve ter entre 2 e 50 letras")
	private String modelo;
	
	@Column(length = 50)
    @NotNull(message = "A marca deve ser preenchido")
    @Size(min = 2, max = 50, message = "A marca deve ter entre 2 e 50 letras")
	private String marca;
	
	@Digits(integer=4, fraction=0, message = "O ano fabricação deve valor inteiro")
	private Integer anoFabricacao;
	
	@Digits(integer=4, fraction=0, message = "O ano modelo deve valor inteiro")
	private Integer anoModelo;
	
	@Column(length = 50)
    @NotNull(message = "A cor deve ser preenchido")
    @Size(min = 2, max = 50, message = "A cor deve ter entre 2 e 50 letras")
	private String cor;
	
	@Column(length = 50)
    @NotNull(message = "A combustivel deve ser preenchido")
    @Size(min = 2, max = 50, message = "A combustivel deve ter entre 2 e 50 letras")
	private String combustivel;
	
	@NotNull(message = "Valor da cilindrada deve ser preenchido")
    @Digits(integer=4, fraction=2, message = "Valor da cilindrada deve ser preenchido com um valor decimal")
	@Min(value = 1, message = "Valor da cilindrada deve ser maior que zero")
	private Double cc;
	
	@Column(length = 50)
    @NotNull(message = "O chassi deve ser preenchido")
    @Size(min = 2, max = 50, message = "O chassi deve ter entre 2 e 50 letras e numeros")
	private String chassi;
	
	@NotNull(message = "Valor da Moto deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "Valor da Moto deve ser preenchido com um valor decimal")
	@Min(value = 1, message = "Valor da Moto deve ser maior que zero")
	private Double valor;
	
	@Column(length = 25)
    @NotNull(message = "A placa deve ser preenchido")
	@Size(min = 6, max = 8, message = "A placa da moto deve ter 7 caracteres")
	private String placa;
	
	@NotNull(message = "O Tipo da moto deve ser preenchido")
	@ManyToOne
	@JoinColumn(name="codTipo")
	private TipoMoto tipoMoto;
	
	@NotNull(message = "O cliente deve ser preenchido")
	@ManyToOne
	@JoinColumn(name="codCliente")
	private Cliente cliente;

}