//Autor
//------------------------------
//Natalia Pitanga
//20171Si017
//------------------------------

package edu.ifes.ci.si.les.scc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scc.model.TaxaComissao;
import edu.ifes.ci.si.les.scc.repositories.TaxaComissaoRepository;
import edu.ifes.ci.si.les.scc.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scc.services.exceptions.ObjectNotFoundException;

@Service
public class TaxaComissaoService {

    @Autowired
    private TaxaComissaoRepository repository;

    public TaxaComissao findById(Integer id) {
    	try {
    		TaxaComissao obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + TaxaComissao.class.getName());
        }
    }

    public Collection<TaxaComissao> findAll() {
        return repository.findAll();
    }

    public TaxaComissao insert(TaxaComissao obj) {
    	obj.setCodTaxaComissao(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Taxa de Comissão não foi(foram) preenchido(s).");
        }
    }

    public TaxaComissao update(TaxaComissao obj) {
        findById(obj.getCodTaxaComissao());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da taxa de comissão.");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esta Taxa de Comissão!");
        }
    }
    
}

