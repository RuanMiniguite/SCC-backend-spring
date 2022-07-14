//Autor
//------------------------------
//Luiz Henrique Cabral
//20181si021
//------------------------------

package edu.ifes.ci.si.les.scc.services;

import java.sql.Date;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scc.model.ItemRevisao;
import edu.ifes.ci.si.les.scc.model.Moto;
import edu.ifes.ci.si.les.scc.model.Produto;
import edu.ifes.ci.si.les.scc.model.Revisao;
import edu.ifes.ci.si.les.scc.model.Venda;
import edu.ifes.ci.si.les.scc.repositories.MotoRepository;
import edu.ifes.ci.si.les.scc.repositories.ProdutoRepository;
import edu.ifes.ci.si.les.scc.repositories.RevisaoRepository;
import edu.ifes.ci.si.les.scc.repositories.VendaRepository;
import edu.ifes.ci.si.les.scc.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scc.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scc.services.exceptions.ObjectNotFoundException;

@Service
public class RevisaoService {
    @Autowired
    private RevisaoRepository repository;
    @Autowired
    private MotoRepository motoRepository;
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    

    public Revisao findById(Integer id) {
    	try {
        	Revisao obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Revisao.class.getName());
        }
    }

    public Collection<Revisao> findAll() {
        return repository.findAll();
    }
     
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Revisao insert(Revisao obj) {
        try {
        	Revisao result = verificarRegrasDeNegocio(obj);
        	if(result != null) {
        		result.setCodRevisao(null);
        		for (ItemRevisao item : result.getItens()) {
					item.setRevisao(result);
					Produto produtoBanco = produtoRepository.findById(item.getProduto().getId()).get();
					// Alterando a qtd de produtos no estoque.
					produtoBanco.setQtd(produtoBanco.getQtd().intValue() - item.getQtd().intValue()); 
					produtoRepository.save(produtoBanco);
				}
        		
        		return repository.save(result);
        	}
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da revisão não foi(foram) preenchido(s).");
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Revisao update(Revisao obj) {
        try {
        	findById(obj.getCodRevisao());
        	
        	Integer dif = 0;
        	for (ItemRevisao itemBanco : repository.findById(obj.getCodRevisao()).get().getItens()) {
        		for (ItemRevisao item : obj.getItens()) {
        			item.setRevisao(obj);
        			String idItemBanco = itemBanco.getRevisao().getCodRevisao().toString().concat(itemBanco.getProduto().getId().toString());
        			
        			String idItem = item.getRevisao().getCodRevisao().toString().concat(item.getProduto().getId().toString());

        			if(idItemBanco.equals(idItem)) {	
        				dif = itemBanco.getQtd().intValue() - item.getQtd().intValue();
        			}
        			
        			if(dif > 0) {
        				Produto produtoBanco = produtoRepository.findById(item.getProduto().getId()).get();
        				// Aumenta qtd de produtos no estoque.
        				produtoBanco.setQtd(produtoBanco.getQtd().intValue() + dif); 
        				produtoRepository.save(produtoBanco);
        			} else {
        				Produto produtoBanco = produtoRepository.findById(item.getProduto().getId()).get();
        				// Diminui qtd de produtos no estoque.
        				produtoBanco.setQtd(produtoBanco.getQtd().intValue() + dif); 
        				produtoRepository.save(produtoBanco);
        			}
        		}
			}
        	
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da revisão não foi(foram) preenchido(s).");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir essa revisão!");
        }
    }
 
 
   	public Revisao verificarRegrasDeNegocio(Revisao obj) {
   
   		//Rule 1 - Verifica se a moto esta paga.
   		Moto moto = motoRepository.findById(obj.getMoto().getCodMoto()).get();
   		Venda venda = vendaRepository.findByCodMoto(moto.getCodMoto()).get();
   		
   		boolean motoQuitada = venda.getPago();
   		
   		if(!motoQuitada) {
   			throw new BusinessRuleException("Esta moto não está quitada!");
   		}
   		
   		//Rule 2 - Caso seja a primeira ou a segunda revisão da moto ela não será cobrada.
   		Integer qtdRevisao = repository.countByCodMoto(moto.getCodMoto()).get();
   		
   		boolean revisaoGratis = false;
   		
   		if(qtdRevisao < 2) {
   			obj.setValor(0.00);
   			revisaoGratis = true;
   		}
   		
   		//Rule 3 - Da primeira até a terceira troca de óleo não será cobrado o valor do item.
   		Integer qtdTrocaOleo = repository.countTrocaOleo(moto.getCodMoto()).get();
   		
   		if(qtdTrocaOleo < 3 && !revisaoGratis) {
 			
   			Double descontoOleo = 0.00;
		
			for (ItemRevisao item : obj.getItens()) {
				Produto produto = item.getProduto();
				if(produto.getNome().equals("Óleo Lubrificante")) {
					descontoOleo = item.getValorUnitario();
					System.out.println("\n\n\n\n ******* " + descontoOleo);
				}
			}
			
			obj.setValor(obj.getValor() - descontoOleo);		   				
   		}
   		
   		if(motoQuitada) {
   			return obj;
   		}else {
   			return null;
   		}  		
   	}
   	
   	public Collection<?> findByRevisaoClienteAndPeriodo(Date inicio, Date termino) {
        return repository.findByRevisaoClienteAndPeriodo(inicio, termino);
    }

}