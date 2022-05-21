package edu.ifes.ci.si.les.scc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scc.model.TaxaComissao;

@Repository
public interface TaxaComissaoRepository extends JpaRepository<TaxaComissao, Integer>{

}
