package edu.ifes.ci.si.les.scc.repositories;

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

}
