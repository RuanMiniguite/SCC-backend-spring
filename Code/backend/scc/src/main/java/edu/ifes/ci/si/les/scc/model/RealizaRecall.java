package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@NotNull(message = "A data da realização do recall deve existir")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	@Digits(integer=1, fraction=0, message = "Valor NULL no admin")
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name="codMoto")
	private Moto moto;

	@ManyToOne
	@JoinColumn(name="codRecall")
	private Recall recall;

}