// Author
// Natalia Pitanga

package edu.ifes.ci.si.les.scc.controllers;

import edu.ifes.ci.si.les.scc.model.ComissaoVenda;
import edu.ifes.ci.si.les.scc.services.ComissaoVendaService;
import edu.ifes.ci.si.les.scc.services.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Collection;

@RestController
@RequestMapping(value = "/comissaovendas")
public class ComissaoVendaController {

    @Autowired
    private ComissaoVendaService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<ComissaoVenda>> findAll() {
        Collection<ComissaoVenda> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ComissaoVenda> find(@PathVariable Integer id) {
        ComissaoVenda obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ComissaoVenda> insert(@Valid @RequestBody ComissaoVenda obj, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ComissaoVenda> update(@Valid @RequestBody ComissaoVenda obj, BindingResult br) {
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

    @RequestMapping(value = "/findByFuncionarioAndPeriodo/{cod_funcionario}/{inicio}/{termino}", method = RequestMethod.GET)
    public ResponseEntity<Collection<?>> findByFuncionarioAndPeriodo(@PathVariable Integer cod_funcionario, @PathVariable Date inicio, @PathVariable Date termino) {
        Collection<?> collection = service.findByFuncionarioAndPeriodo(cod_funcionario, inicio, termino);
        return ResponseEntity.ok().body(collection);
    }

}
