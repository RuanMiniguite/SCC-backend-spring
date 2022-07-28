//Autor
//------------------------------
//Natalia Pitanga
//20171Si017
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

import edu.ifes.ci.si.les.scc.model.TaxaComissao;
import edu.ifes.ci.si.les.scc.services.TaxaComissaoService;
import edu.ifes.ci.si.les.scc.services.exceptions.ConstraintException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/taxacomissao")
public class TaxaComissaoController {

    @Autowired
    private TaxaComissaoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<TaxaComissao>> findAll() {
        Collection<TaxaComissao> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TaxaComissao> find(@PathVariable Integer id) {
    	TaxaComissao obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TaxaComissao> insert(@Valid @RequestBody TaxaComissao obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TaxaComissao> update(@Valid @RequestBody TaxaComissao obj, BindingResult br) {
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

