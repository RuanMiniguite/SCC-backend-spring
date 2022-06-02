package edu.ifes.ci.si.les.scc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scc.model.Revisao;
import edu.ifes.ci.si.les.scc.repositories.RevisaoRepository;
import edu.ifes.ci.si.les.scc.services.exceptions.ObjectNotFoundException;

@Service
public class RevisaoService {
	@Autowired
	private RevisaoRepository revisaoRepository;
	
	public Revisao findById(Integer id) {
		try {
			Revisao obj = revisaoRepository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Revisao.class.getName());
        }
	}
	public Collection<Revisao> findAll() {
		return revisaoRepository.findAll();
	}

}
