//Autor
//------------------------------
//Luiz Henrique Cabral
//20181si021
//------------------------------

package edu.ifes.ci.si.les.scc.controllers;

import java.sql.Date;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.scc.model.Revisao;
import edu.ifes.ci.si.les.scc.services.RevisaoService;
import edu.ifes.ci.si.les.scc.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/revisoes")
public class RevisaoController {
	
	@Autowired
    private RevisaoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Revisao>> findAll() {
        Collection<Revisao> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Revisao> find(@PathVariable Integer id) {
    	Revisao obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Revisao> insert(@Valid @RequestBody Revisao obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Revisao> update(@Valid @RequestBody Revisao obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/findByRevisaoClienteAndPeriodo/{inicio}/{termino}", method = RequestMethod.GET)
    public ResponseEntity<Collection<?>> findByRevisaoClienteAndPeriodo(@PathVariable Date inicio, @PathVariable Date termino) {
        Collection<?> collection = service.findByRevisaoClienteAndPeriodo(inicio, termino);
        return ResponseEntity.ok().body(collection);
    }

}
