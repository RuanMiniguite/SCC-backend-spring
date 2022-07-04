//Autor
//------------------------------
//Ruan Pezzin Miniguite
//20181si018
//------------------------------

package edu.ifes.ci.si.les.scc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scc.model.Cliente;
import edu.ifes.ci.si.les.scc.model.Funcionario;
import edu.ifes.ci.si.les.scc.model.Moto;
import edu.ifes.ci.si.les.scc.model.Venda;
import edu.ifes.ci.si.les.scc.repositories.ClienteRepository;
import edu.ifes.ci.si.les.scc.repositories.FuncionarioRepository;
import edu.ifes.ci.si.les.scc.repositories.MotoRepository;
import edu.ifes.ci.si.les.scc.repositories.VendaRepository;
import edu.ifes.ci.si.les.scc.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scc.services.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.scc.services.exceptions.BusinessRuleException;

@Service
public class VendaService {
    @Autowired
    private VendaRepository repository;
    @Autowired
    private MotoRepository motoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    

    public Venda findById(Integer id) {
    	try {
        	Venda obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Venda.class.getName());
        }
    }

    public Collection<Venda> findAll() {
        return repository.findAll();
    }
     
    public Venda insert(Venda obj) {
        try {
        	if(verificarRegrasDeNegocio(obj)) {
        		obj.setCodVenda(null);
        		return repository.save(obj);
        	}
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da venda não foi(foram) preenchido(s).");
        }
        return null;
    }
    
    public Venda update(Venda obj) {
        findById(obj.getCodVenda());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da venda não foi(foram) preenchido(s).");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esta venda!");
        }
    }
 
   	public boolean verificarRegrasDeNegocio(Venda obj) {
   
   		//Rule 1 - Customer in debt
   		Collection<Venda> clienteEmDebito = repository.findByClienteDebito(obj.getCliente().getCodCliente());
   		boolean clienteDevedor = false;	
 
   		if(clienteEmDebito.size() > 0) {
   			throw new BusinessRuleException("Este cliente possui debitos!");
   		}else {
   			clienteDevedor = true;
   		}
   		
   		
   		//Rule 2 - Motorcycle In Stock
   		Moto moto = motoRepository.findById(obj.getMoto().getCodMoto()).get();
   		boolean motosEmEstoque = false;	
   		
   		if(moto.getCliente() != null) {
   			throw new BusinessRuleException("A Moto Já possui Cliente!");
   		}else {
   			motosEmEstoque = true;
   		}	

   		
   		//Rule 3 - Customer is an employee 10%
   		Cliente cliente = clienteRepository.findById(obj.getCliente().getCodCliente()).get();
   		Funcionario funcionario = funcionarioRepository.findById(obj.getFuncionario().getCodFuncionario()).get();
   		Double desconto;
   		boolean descontoRealizado = false;
   	
   		if(cliente.getCpf() == funcionario.getCpf()){
   			desconto = ((obj.getValor() * 10) / 100);
   		}else{
   			//Rule 4 - Customer has already made a purchase
   			Collection<Venda> clienteDesconto = repository.findByCliente(obj.getCliente().getCodCliente());
   			if(clienteDesconto.size() > 0) {
   				desconto = ((obj.getValor() * 5) / 100);
   	   		}else{
   	   			desconto = 0.0;
   	   		}
   		}
   		
   		if(desconto.equals(obj.getDesconto())) {
   			descontoRealizado = true;
		}else {
			throw new BusinessRuleException("O valor do desconto está incorreto! - Desconto: " + desconto + " Valor Realizado: " + obj.getDesconto());
		}
   		
   		
   		if(clienteDevedor && motosEmEstoque && descontoRealizado) {
   			return true;
   		}else {
   			return false;
   		}  		
   	}

}



