//Author
// Natalia Pitanga

package edu.ifes.ci.si.les.scc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scc.model.ComissaoVenda;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ComissaoVendaRepository extends JpaRepository<ComissaoVenda, Integer>{
    @Transactional(readOnly = true)
    @Query(value = "SELECT cv.* FROM comissao_venda cv WHERE cv.cod_venda = ?1", nativeQuery = true)
    public ComissaoVenda findByVenda(Integer cod_venda);
}
