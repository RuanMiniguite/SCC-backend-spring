//PATRICIA - 20181SI004

package edu.ifes.ci.si.les.scc.repositories;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scc.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT ID FROM PRODUTO WHERE NOME = 'Óleo Lubrificante';", nativeQuery = true)
	public Collection<Integer> getProdutosByNome();

	@Transactional(readOnly = true)
    @Query(value = "SELECT SUM(REV.QTD) AS QTD, pro.marca, pro.nome FROM ITEM_REVISAO REV INNER JOIN PRODUTO PRO ON (PRO.ID = REV.PRODUTO_ID)  INNER JOIN REVISAO REVI ON (REVI.COD_REVISAO = REV.REVISAO_ID)  WHERE REVI.DATA BETWEEN ?1 AND ?2 group by pro.marca, pro.nome", nativeQuery = true)
    public Collection<?> findByProdutosPorPeriodo(Date inicio, Date termino);
}
