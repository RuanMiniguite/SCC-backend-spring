package edu.ifes.ci.si.les.scc.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scc.model.RealizaRecall;

@Repository
public interface RealizaRecallRepository extends JpaRepository<RealizaRecall, Integer>{
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT  * FROM REALIZA_RECALL WHERE COD_MOTO = ?1 AND STATUS = 1", nativeQuery = true)
	public Collection<RealizaRecall> findByRecallRealizado(Integer cod_moto);
}
