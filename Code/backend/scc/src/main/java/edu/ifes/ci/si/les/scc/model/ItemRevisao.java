package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ItemRevisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
    @EmbeddedId
	private ItemRevisaoPK id = new ItemRevisaoPK();

	@NotNull(message = "Valor Unitario deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "Valor Unitario deve ser preenchido com dígitos")
	@Min(value = 1, message = "Valor do produto deve ser maior que zero")
	private Double valorUnitario;
	
	@Digits(integer=5, fraction=0, message = "A quantidade do Produto deve ser preenchido com um valor inteiro")
	private Integer qtd;
	
    @Builder
    public ItemRevisao(Revisao revisao, Produto produto, Double valorUnitario, Integer qtd) {
        this.id.setRevisao(revisao);
        this.id.setProduto(produto);
        this.valorUnitario = valorUnitario;
        this.qtd = qtd;
    }
    
    @JsonIgnore
    public Revisao getRevisao() {
        return id.getRevisao();
    }

    public void setRevisao(Revisao revisao) {
        id.setRevisao(revisao);
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

}
