//Autor
//------------------------------
//Ruan Pezzin Miniguite
//20181si018
//------------------------------

package edu.ifes.ci.si.les.scc.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.scc.model.TipoMoto;
import edu.ifes.ci.si.les.scc.services.TipoMotoService;
import edu.ifes.ci.si.les.scc.services.exceptions.ConstraintException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/tipomotos")
public class TipoMotoController {
    
	@Autowired
    private TipoMotoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<TipoMoto>> findAll() {
        Collection<TipoMoto> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TipoMoto> find(@PathVariable Integer id) {
    	TipoMoto obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TipoMoto> insert(@Valid @RequestBody TipoMoto obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TipoMoto> update(@Valid @RequestBody TipoMoto obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
