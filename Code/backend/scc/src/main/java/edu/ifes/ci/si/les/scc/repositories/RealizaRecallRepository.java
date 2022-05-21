package edu.ifes.ci.si.les.scc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scc.model.RealizaRecall;

@Repository
public interface RealizaRecallRepository extends JpaRepository<RealizaRecall, Integer>{

}
