//Autor
//------------------------------
//Ruan Pezzin Miniguite
//20181si018
//------------------------------


package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codFuncionario"})
public class Funcionario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codFuncionario;
	
	@Column(length = 50)
    @NotNull(message = "Nome do Funcionário deve ser preenchido")
    @Size(min = 2, max = 50, message = "Nome do funcionário deve ter entre 2 e 50 letras")
	private String nome;
	
	@Column(length = 50)
    @NotNull(message = "CPF do Funcionário deve ser preenchido")
    @Size(min = 2, max = 50, message = "CPF do funcionário deve ter entre 2 e 50 letras")
    @Pattern(regexp="\\d{3}.\\d{3}.\\d{3}-\\d{2}", message = "CPF do funcionário deve seguir o padrão NNN.NNN.NNN-NN")
	private String cpf;
	
	@Column(length = 25)
    @NotNull(message = "O Telefone do Funcionário deve ser preenchido")
    @Size(min = 9, max = 25, message = "O Telefone do Funcionário deve ter 9 caracteres")
    @Pattern(regexp="\\(\\d{2}\\)\\d{5}-\\d{4}", message = "O Telefone do funcionário deve seguir o padrão (NN)NNNNN-NNNN") 
	private String telefone;
	
	@NotNull(message = "Nascimento do Funcionário deve ser preenchido")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;
	
	@Column(length = 25)
	@NotNull(message = "Nome do Estado deve ser preenchido")
	@Size(min = 2, max = 25, message = "Nome do Estado deve ter entre 2 e 25 letras")
	private String estado;
	
	@Column(length = 25)
    @NotNull(message = "O CEP do Funcionário deve ser preenchido")
    @Size(min = 8, max = 10, message = "O CEP do Funcionário deve ter 9 caracteres")
    @Pattern(regexp="\\d{5}-\\d{3}", message = "CPF do funcionário deve seguir o padrão NNNNN-NNN") 
	private String cep;
	
	@Column(length = 50)
	@NotNull(message = "Nome da Cidade deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome da Cidade deve ter entre 2 e 50 letras")
	private String cidade;
	
	@Column(length = 50)
	@NotNull(message = "Nome do Bairro deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome do Bairro deve ter entre 2 e 50 letras")
	private String bairro;
	
	@Column(length = 20)
    @NotNull(message = "Login do Funcionário deve ser preenchido")
    @Size(min = 2, max = 50, message = "Login do Funcionário deve ter entre 2 e 20 caracteres")
	private String login;
	
    @Column(length = 20)
    @NotNull(message = "Senha do Funcionário deve ser preenchida")
    @Size(min = 6, max = 10, message = "Senha do Funcionário deve ter entre 6 e 20 caracteres")
	private String senha;
    
	@Column(length = 25)
	@NotNull(message = "O cargo deve ser preenchido")
	@Size(min = 2, max = 25, message = "O cargo deve ter entre 2 e 25 letras")
	private String cargo;
	
	@NotNull(message = "Valor do Salário deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "Valor do Salário deve ser preenchido com dígitos")
	@Min(value = 1, message = "Valor do Salário deve ser maior que zero")
	private Double salario;
	
	@Digits(integer=1, fraction=0, message = "Valor NULL no admin")
	private Integer admin;

}
