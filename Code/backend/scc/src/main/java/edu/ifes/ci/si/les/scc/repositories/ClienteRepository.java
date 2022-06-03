//Autor
//------------------------------
//Patricia Regina Daros
//20181si004
//------------------------------

package edu.ifes.ci.si.les.scc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.ifes.ci.si.les.scc.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
