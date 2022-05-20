package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@Embeddable
@Data
@EqualsAndHashCode(of = {"produto", "revisao"})
public class itemRevisaoPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "codProduto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "codRevisao")
    private Revisao revisao;
    

}
