//Author
// Natalia Pitanga

package edu.ifes.ci.si.les.scc.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import edu.ifes.ci.si.les.scc.model.Funcionario;
import edu.ifes.ci.si.les.scc.repositories.FuncionarioRepository;
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

    @Autowired
    private FuncionarioRepository funcionarioRepository;

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

    public Optional<TaxaComissao> findByFuncionario(Integer funcionarioId) {
        Integer anoCasa = null;
        Funcionario func;
        try {
            Optional<Funcionario> funcionario = funcionarioRepository.findById(funcionarioId);

            if(funcionario.isPresent()) {
                func = funcionario.get();
                Date dataAtual = new Date();
                LocalDate d1 = func.getDataAdmissao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate d2 = dataAtual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
                anoCasa = Math.toIntExact(diff.toDays() / 365);

                Collection<TaxaComissao> taxaComissoes = repository.findByCargoAndAnoCasa(func.getCargo(), anoCasa);

                return taxaComissoes.stream().reduce((taxaComissao, taxaComissao2) -> taxaComissao.getAnoCasa() > taxaComissao2.getAnoCasa() ? taxaComissao : taxaComissao2);
            } else {
                return Optional.empty();
            }
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Cargo: , AnoCasa: " + anoCasa + ", Tipo: " + TaxaComissao.class.getName());
        }
    }
    
}

