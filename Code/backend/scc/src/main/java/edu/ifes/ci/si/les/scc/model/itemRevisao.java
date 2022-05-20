package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class itemRevisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
    @EmbeddedId
	private itemRevisaoPK id = new itemRevisaoPK();
	
	private String nome;

	private Double valorUnitario;

	private Integer qtd;

}
