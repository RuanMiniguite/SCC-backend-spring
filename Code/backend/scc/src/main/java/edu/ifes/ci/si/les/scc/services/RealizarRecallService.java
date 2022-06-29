//Autor
//------------------------------
//Patricia Regina Daros
//20181si004
//------------------------------

package edu.ifes.ci.si.les.scc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scc.model.Moto;
import edu.ifes.ci.si.les.scc.model.RealizaRecall;
import edu.ifes.ci.si.les.scc.model.Venda;
import edu.ifes.ci.si.les.scc.repositories.RealizaRecallRepository;
import edu.ifes.ci.si.les.scc.repositories.RecallRepository;
import edu.ifes.ci.si.les.scc.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scc.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scc.services.exceptions.ObjectNotFoundException;

@Service
public class RealizarRecallService {

    @Autowired
    private RealizaRecallRepository repository;

    public RealizaRecall findById(Integer id) {
    	RealizaRecall obj = repository.findById(id).get();
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + RealizaRecall.class.getName());
        }
        return obj;
    }

    public Collection<RealizaRecall> findAll() {
        return repository.findAll();
    }

    public RealizaRecall insert(RealizaRecall obj) {
        try {
        	if (verificarRegrasDeNegocio(obj)) {
        		obj.setCodRealizarRecall(null);
        		return repository.save(obj);
        	}
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da realização do Recall não foi(foram) preenchido(s)");
        }
        return null;
    }

    public RealizaRecall update(RealizaRecall obj) {
    	findById(obj.getCodRealizarRecall());
    	
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da realização Recall não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esta realização de Recall!");
        }
    }

    public boolean verificarRegrasDeNegocio(RealizaRecall obj) {
    	   
    	Collection<RealizaRecall> recall = repository.findByRecallRealizado(obj.getMoto().getCodMoto(), obj.getRecall().getCodRecall());
    	Collection<RealizaRecall> quatidade_recall = repository.findByQuatidadeRecalls(obj.getData());
    	boolean recallsRealizar = false;	
 
   		if(recall.size() > 0) {
   			throw new BusinessRuleException("Esta moto já possui recall cadastrado! !");
   		}else {
   			recallsRealizar = true;
   		}
   		
   		if(quatidade_recall.size() > 3) {
   			throw new BusinessRuleException("Quantidade de Recalls excedida no data!");
   		}else {
   			recallsRealizar = true;
   		}
   			
   		return recallsRealizar;
   		
    }
}