//Autor
//------------------------------
//Ruan Pezzin Miniguite
//20181si018
//------------------------------

package edu.ifes.ci.si.les.scc.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import edu.ifes.ci.si.les.scc.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer>{
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT venda.* FROM venda WHERE venda.pago = 'false' AND venda.cod_cliente = ?1", nativeQuery = true)
	public Collection<Venda> findByClienteDebito(Integer cod_cliente);
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT venda.* FROM venda WHERE venda.cod_cliente = ?1", nativeQuery = true)
	public Collection<Venda> findByCliente(Integer cod_cliente);
	
}
