//Autor
//------------------------------
//Ruan Pezzin Miniguite
//20181si018
//------------------------------

package edu.ifes.ci.si.les.scc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scc.model.TipoMoto;
import edu.ifes.ci.si.les.scc.repositories.TipoMotoRepository;
import edu.ifes.ci.si.les.scc.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scc.services.exceptions.ObjectNotFoundException;

@Service
public class TipoMotoService {

  @Autowired
  private TipoMotoRepository repository;

  public TipoMoto findById(Integer id) {
  	try {
  		TipoMoto obj = repository.findById(id).get();
      	return obj;
      } catch (NoSuchElementException e) {
      	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + TipoMoto.class.getName());
      }	
  }

  public Collection<TipoMoto> findAll() {
      return repository.findAll();
  }

  public TipoMoto insert(TipoMoto obj) {
  	obj.setCodTipo(null);
      try {
      	return repository.save(obj);
      } catch (DataIntegrityViolationException e) {
          throw new DataIntegrityException("Campo(s) obrigatório(s) do Funcionário não foi(foram) preenchido(s).");
      }
  }

  public TipoMoto update(TipoMoto obj) {
      findById(obj.getCodTipo());
      try {
      	return repository.save(obj);
      } catch (DataIntegrityViolationException e) {
          throw new DataIntegrityException("Campo(s) obrigatório(s) do Funcionário não foi(foram) preenchido(s).");
      }
  }

  public void delete(Integer id) {
      findById(id);
      try {
          repository.deleteById(id);
      } catch (DataIntegrityViolationException e) {
          throw new DataIntegrityException("Não é possível excluir este Funcionário!");
      }
  }
  
}
