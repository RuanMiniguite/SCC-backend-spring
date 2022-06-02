package edu.ifes.ci.si.les.scc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scc.model.Moto;
import edu.ifes.ci.si.les.scc.repositories.MotoRepository;
import edu.ifes.ci.si.les.scc.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scc.services.exceptions.ObjectNotFoundException;

@Service
public class MotoService {
	@Autowired
	private MotoRepository motoRepository;
	
	public Moto findById(Integer codMoto) {
		try {
			Moto obj = motoRepository.findById(codMoto).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + codMoto + ", Tipo: " + Moto.class.getName());
        }
	}
	public Collection<Moto> findAll() {
		return motoRepository.findAll();
	}
	
    public Moto insert(Moto obj) {
    	obj.setCodMoto(null);
        try {
        	return motoRepository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Moto não foi(foram) preenchido(s)");
        }
    }

    public Moto update(Moto obj) {
    	findById(obj.getCodMoto());
        try {
        	return motoRepository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Moto não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer codMoto) {
        findById(codMoto);
        try {
        	motoRepository.deleteById(codMoto);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Moto vinculada a Itens de Empréstimos!");
        }
    }

}
