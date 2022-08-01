//Autor
//------------------------------
//Ruan Pezzin Miniguite
//20181si018
//------------------------------

package edu.ifes.ci.si.les.scc.repositories;

import java.util.Collection;
import java.sql.Date;
import java.util.Optional;

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
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT venda.* FROM VENDA WHERE COD_MOTO = ?1", nativeQuery = true)
	public Optional<Venda> findByCodMoto(Integer codMoto);
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT SUM(venda.valor) AS total, func.cod_funcionario, func.nome FROM venda INNER JOIN funcionario func on (func.cod_funcionario = venda.cod_funcionario) WHERE venda.data BETWEEN ?1 AND ?2 GROUP BY func.cod_funcionario, func.nome", nativeQuery = true)
	public Collection<?> findByVendaFuncionarioAndPeriodo(Date inicio, Date termino);

	@Transactional(readOnly = true)
	@Query(value = "SELECT v.*, m.modelo, m.marca FROM venda v INNER JOIN funcionario f ON f.cod_funcionario = v.cod_funcionario INNER JOIN moto m ON m.cod_moto = v.cod_moto WHERE f.cod_funcionario = ?1", nativeQuery = true)
	public Collection<Venda> findByFuncionario(Integer funcionarioId);

}
