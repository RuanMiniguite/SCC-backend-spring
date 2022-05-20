package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;

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

	private String modelo;

	private String marca;

	private Integer anoFabricacao;

	private Integer anoModelo;

	private String cor;

	private String combustivel;

	private Double cc;

	private String chassi;

	private Double valor;

	private String placa;

	@ManyToOne
	@JoinColumn(name="codTipo")
	private TipoMoto tipoMoto;
	
	@ManyToOne
	@JoinColumn(name="codRecall")
	private Cliente cliente;

}