package edu.ifes.ci.si.les.scc.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.scc.model.Revisao;
import edu.ifes.ci.si.les.scc.services.RevisaoService;

@RestController
@RequestMapping(value = "/revisao")
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

}
