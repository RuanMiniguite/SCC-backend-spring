//Autor
//------------------------------
//Patricia Regina Daros
//20181si004
//------------------------------

package edu.ifes.ci.si.les.scc.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scc.model.Cliente;
import edu.ifes.ci.si.les.scc.repositories.ClienteRepository;
import edu.ifes.ci.si.les.scc.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente findById(Integer id) {
        Cliente obj = repository.findById(id).get();
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
        }
        return obj;
    }

    public Collection<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente insert(Cliente obj) {
    	obj.setCodCliente(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Cliente não foi(foram) preenchido(s)");
        }
    }

    public Cliente update(Cliente obj) {
    	findById(obj.getCodCliente());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Cliente não foi(foram) preenchido(s)");
        }
    }
    
    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir este Cliente!");
        }
    }
}
