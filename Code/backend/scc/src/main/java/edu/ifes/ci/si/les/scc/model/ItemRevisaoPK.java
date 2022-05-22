package edu.ifes.ci.si.les.scc.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.*;

@Data
@Embeddable
@EqualsAndHashCode(of = {"revisao", "produto"})
public class ItemRevisaoPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "revisao_id")
    private Revisao revisao;
    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
}
