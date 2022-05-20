package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.*;
import edu.ifes.ci.si.les.scc.model.enums.Status;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codRealizarRecall"})
public class RealizaRecall implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codRealizarRecall;

	private Date data;

	private Status status;
	
	@ManyToOne
	@JoinColumn(name="codMoto")
	private Moto moto;

	@ManyToOne
	@JoinColumn(name="codRecall")
	private Recall recall;

}
