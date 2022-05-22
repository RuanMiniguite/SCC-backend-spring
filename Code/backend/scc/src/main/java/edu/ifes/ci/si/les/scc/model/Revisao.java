package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Revisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "A data da Revisão deve ser preenchido")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	@Min(value = 0, message = "O valor da revisão não pode ser menor que 0 (Zero)")
	@NotNull(message = "Valor da revisão deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "Valor da revisão deve ser preenchido com dígitos")
	private Double valor;
	
	@NotNull(message = "O Funcionario deve ser preenchido")
	@ManyToOne
	@JoinColumn(name="codFuncionario")
	private Funcionario funcionario;
	
	@NotNull(message = "A Moto deve ser preenchido")
	@ManyToOne
	@JoinColumn(name="codMoto")
	private Moto moto;
	
    @NotNull(message = "A revisão deve possuir pelo menos um Item de Revisão")
    //orphanRemoval = true: utilizado para remover filhos (itens) sem pai (empréstimo) em caso de atualizaçao do empréstimo (para um número de itens menor que o anterior)
    @OneToMany(mappedBy = "id.revisao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ItemRevisao> itens = new ArrayList<>();
    
    @Builder
    public Revisao(Integer id, Date data, Double valor, Funcionario funcionario, Moto moto) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.funcionario = funcionario;
        this.moto = moto;
    }

}
