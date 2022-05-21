package edu.ifes.ci.si.les.scc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scc.model.itemRevisao;

@Repository
public interface itemRevisaoRepository extends JpaRepository<itemRevisao, Integer>{

}
