//Author
// Natalia Pitanga

package edu.ifes.ci.si.les.scc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scc.model.TaxaComissao;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public interface TaxaComissaoRepository extends JpaRepository<TaxaComissao, Integer>{
    @Transactional(readOnly = true)
    @Query(value = "SELECT tc.* FROM taxa_comissao tc WHERE tc.cargo = ?1 AND tc.ano_casa <= ?2", nativeQuery = true)
    public Collection<TaxaComissao> findByCargoAndAnoCasa(String cargo, Integer ano_casa);
}
