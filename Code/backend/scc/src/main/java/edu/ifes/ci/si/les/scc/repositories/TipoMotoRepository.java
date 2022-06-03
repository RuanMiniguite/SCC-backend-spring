//Autor
//------------------------------
//Ruan Pezzin Miniguite
//20181si018
//------------------------------

package edu.ifes.ci.si.les.scc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scc.model.TipoMoto;

@Repository
public interface TipoMotoRepository extends JpaRepository<TipoMoto, Integer>{

}
