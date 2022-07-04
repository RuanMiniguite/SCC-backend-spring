//Autor
//------------------------------
//Luiz Henrique Cabral
//20181si021
//------------------------------

package edu.ifes.ci.si.les.scc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scc.model.Revisao;

@Repository
public interface RevisaoRepository extends JpaRepository<Revisao, Integer>{
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT COUNT(*) FROM revisao WHERE COD_MOTO = ?1", nativeQuery = true)
	public Optional<Integer> countByCodMoto(Integer codMoto);
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT COUNT(*), FROM revisao INNER JOIN ITEM_REVISAO ON REVISAO.COD_REVISAO=ITEM_REVISAO.REVISAO_ID INNER JOIN produto ON produto.ID =ITEM_REVISAO.PRODUTO_ID WHERE revisao.cod_moto = ?1 and produto.nome='Óleo Lubrificante';", nativeQuery = true)
	public Optional<Integer> countTrocaOleo(Integer codMoto);
	
}