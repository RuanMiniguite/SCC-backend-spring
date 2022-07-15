//Author
// Natalia Pitanga

package edu.ifes.ci.si.les.scc.services;

import edu.ifes.ci.si.les.scc.model.ComissaoVenda;
import edu.ifes.ci.si.les.scc.model.Funcionario;
import edu.ifes.ci.si.les.scc.model.TaxaComissao;
import edu.ifes.ci.si.les.scc.model.Venda;
import edu.ifes.ci.si.les.scc.repositories.ComissaoVendaRepository;
import edu.ifes.ci.si.les.scc.repositories.FuncionarioRepository;
import edu.ifes.ci.si.les.scc.repositories.VendaRepository;
import edu.ifes.ci.si.les.scc.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scc.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class ComissaoVendaService {

    @Autowired
    private ComissaoVendaRepository repository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private TaxaComissaoService taxaComissaoService;

    public ComissaoVenda findById(Integer id) {
        try {
            ComissaoVenda obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ComissaoVenda.class.getName());
        }
    }

    public Collection<ComissaoVenda> findAll() {
        return repository.findAll();
    }

    public ComissaoVenda insert(ComissaoVenda obj) {
        obj.setCodComissaoVenda(null);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Funcionario funcionario = null;
            try {
                funcionario = funcionarioRepository.findById(obj.getVenda().getFuncionario().getCodFuncionario()).get();
            } catch (Exception e) {
                throw new BusinessRuleException("Funcionário não encontrado!");
            }

            if (funcionario.getCodFuncionario() > 0) {
                LocalDate d1 = obj.getDataIni().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int ano = d1.getYear();
                int mes = d1.getMonthValue();
                int dia = d1.getDayOfMonth();
                String dataInicio = ano + "-" + mes + "-" + dia;

                LocalDate d2 = obj.getDataIni().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                ano = d2.getYear();
                mes = d2.getMonthValue();
                dia = d2.getDayOfMonth();
                String dataFim = ano + "-" + mes + "-" + dia;

                Venda venda;
                try {
                    venda = vendaRepository.findById(obj.getVenda().getCodVenda()).get();
                } catch (Exception e) {
                    throw new BusinessRuleException("Venda não encontrada!");
                }

                ComissaoVenda temp = repository.findByVenda(venda.getCodVenda());

                if(temp != null && temp.getCodComissaoVenda() > 0) {
                    throw new BusinessRuleException("Já existe uma Comissão Venda para a venda informada!");
                }

                if (venda.getPago()) {
                    TaxaComissao taxaComissao;
                    try {
                        taxaComissao = taxaComissaoService.findByFuncionario(funcionario.getCodFuncionario()).get();
                    } catch (Exception e) {
                        throw new BusinessRuleException("Taxa Comissão não encontrada!");
                    }

                    if(taxaComissao.getCodTaxaComissao() > 0) {
                        ComissaoVenda cV = new ComissaoVenda();

                        Double valor = (venda.getValor() - venda.getDesconto()) * (taxaComissao.getTaxa() / 100);
                        long factor = (long) Math.pow(10, 2);
                        valor = valor * factor;
                        long tmp = Math.round(valor);
                        valor = (double) tmp / factor;

                        cV.setTaxaComissao(taxaComissao);
                        cV.setValorTotal(valor);
                        cV.setDataIni(sdf.parse(dataInicio));
                        cV.setDataFim(sdf.parse(dataFim));
                        cV.setVenda(venda);
                        cV.setPago(false);

                        try {
                            return repository.save(cV);
                        } catch (DataIntegrityViolationException e) {
                            throw new DataIntegrityException("Campo(s) obrigatório(s) da ComissaoVenda não foi(foram) preenchido(s).");
                        }
                    } else {
                        throw new BusinessRuleException("Taxa Comissão não encontrada!");
                    }
                } else {
                    throw new BusinessRuleException("Nenhuma comissão para receber!");
                }
            } else {
                throw new BusinessRuleException("Funcionário não encontrado!");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    public ComissaoVenda update(ComissaoVenda obj) {
        findById(obj.getCodComissaoVenda());
        try {
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da ComissaoVenda não foi(foram) preenchido(s).");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esta ComissaoVenda!");
        }
    }

    public Collection<?> findByFuncionarioAndPeriodo(Integer cod_funcionario, Date inicio, Date termino) {
        return repository.findByFuncionarioAndPeriodo(cod_funcionario, inicio, termino);
    }

}
