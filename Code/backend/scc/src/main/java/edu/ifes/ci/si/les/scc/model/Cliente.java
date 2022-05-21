package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import javax.persistence.*;
import edu.ifes.ci.si.les.scc.model.enums.Pessoa;
import lombok.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codCliente"})
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codCliente;
	
	@Column(length = 50)
    @NotBlank(message = "Nome do cliente deve ser preenchido")
    @Size(min = 2, max = 50, message = "Nome do cliente deve ter entre 2 e 50 letras")
	private String nome;
	
	@Column(length = 50)
    @NotBlank(message = "CPF do cliente deve ser preenchido")
    @Size(min = 2, max = 50, message = "CPF do cliente deve ter entre 2 e 50 Numeros")
    @Pattern(regexp="\\d{3}.\\d{3}.\\d{3}-\\d{2}", message = "CPF do cliente deve seguir o padrão NNN.NNN.NNN-NN")
	private String cpf;
	
	@Column(length = 50)
    @NotBlank(message = "O E-mail deve ser preenchido")
    @Size(min = 2, max = 50, message = "Nome do E-mail deve ter entre 2 e 50 letras")
	private String email;
	
	@Column(length = 25)
    @NotBlank(message = "O Telefone do cliente deve ser preenchido")
    @Size(min = 9, max = 15, message = "O Telefone do cliente deve ter 9 caracteres")
    @Pattern(regexp="(d{2})\\d{5}-\\d{4}", message = "O Telefone do cliente deve seguir o padrão (NN)NNNNN-NNNN") 
	private String telefone;
	
	@Column(length = 25)
	@NotBlank(message = "Nome do Estado deve ser preenchido")
	@Size(min = 2, max = 25, message = "Nome do Estado deve ter entre 2 e 25 letras")
	private String estado;
	
	@Column(length = 25)
    @NotBlank(message = "O CEP do cliente deve ser preenchido")
    @Size(min = 8, max = 10, message = "O CEP do cliente deve ter 9 caracteres")
    @Pattern(regexp="\\d{5}-\\d{3}", message = "CPF do cliente deve seguir o padrão NNNNN-NNN") 
	private String cep;
	
	@Column(length = 50)
	@NotBlank(message = "Nome da Cidade deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome da Cidade deve ter entre 2 e 50 letras")
	private String cidade;
	
	@Column(length = 50)
	@NotBlank(message = "Nome do Bairro deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome do Bairro deve ter entre 2 e 50 letras")
	private String bairro;
	
	@Digits(integer=1, fraction=0, message = "Valor NULL no pessoa")
	private Pessoa pessoa;

}
