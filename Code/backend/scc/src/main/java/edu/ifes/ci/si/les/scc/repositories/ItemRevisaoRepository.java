package edu.ifes.ci.si.les.scc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scc.model.ItemRevisao;
import edu.ifes.ci.si.les.scc.model.ItemRevisaoPK;

@Repository
public interface ItemRevisaoRepository extends JpaRepository<ItemRevisao, ItemRevisaoPK>{

}
