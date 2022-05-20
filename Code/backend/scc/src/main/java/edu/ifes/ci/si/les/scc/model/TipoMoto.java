package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codTipo"})
public class TipoMoto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codTipo;

	private String nome;

}
