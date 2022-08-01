//Author
// Natalia Pitanga

package edu.ifes.ci.si.les.scc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scc.model.ComissaoVenda;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collection;

@Repository
public interface ComissaoVendaRepository extends JpaRepository<ComissaoVenda, Integer>{
    @Transactional(readOnly = true)
    @Query(value = "SELECT cv.* FROM comissao_venda cv WHERE cv.cod_venda = ?1", nativeQuery = true)
    public ComissaoVenda findByVenda(Integer cod_venda);

    @Transactional(readOnly = true)
    @Query(value = "SELECT SUM(venda.valor) AS total, func.cod_funcionario, func.nome FROM venda INNER JOIN funcionario func on (func.cod_funcionario = venda.cod_funcionario) WHERE venda.data BETWEEN ?1 AND ?2 GROUP BY func.cod_funcionario, func.nome", nativeQuery = true)
    public Collection<?> findByFuncionarioAndPeriodo(Date inicio, Date termino);

    @Transactional(readOnly = true)
    @Query(value = "SELECT SUM(cv.valor_total) AS total, cv.*\n" +
            "    FROM comissao_venda cv\n" +
            "    INNER JOIN venda v ON v.cod_venda = cv.cod_venda\n" +
            "    INNER JOIN funcionario f ON f.cod_funcionario = v.cod_funcionario\n" +
            "    WHERE f.cod_funcionario = ?1\n" +
            "    AND cv.pago = true\n" +
            "    AND cv.data_ini BETWEEN ?2 AND ?3\n" +
            "    AND cv.data_fim BETWEEN ?2 AND ?3\n" +
            "    GROUP BY cv.cod_comissao_venda", nativeQuery = true)
    public Collection<ComissaoVenda> findByFuncionarioAndPeriodo(Integer cod_funcionario, Date inicio, Date termino);
}
