package edu.ifes.ci.si.les.scc.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import edu.ifes.ci.si.les.scc.model.Cliente;
import edu.ifes.ci.si.les.scc.model.ComissaoVenda;
import edu.ifes.ci.si.les.scc.model.Funcionario;
import edu.ifes.ci.si.les.scc.model.itemRevisao;
import edu.ifes.ci.si.les.scc.model.Moto;
import edu.ifes.ci.si.les.scc.model.Produto;
import edu.ifes.ci.si.les.scc.model.RealizaRecall;
import edu.ifes.ci.si.les.scc.model.Recall;
import edu.ifes.ci.si.les.scc.model.Revisao;
import edu.ifes.ci.si.les.scc.model.TaxaComissao;
import edu.ifes.ci.si.les.scc.model.TipoMoto;
import edu.ifes.ci.si.les.scc.model.Venda;

import edu.ifes.ci.si.les.scc.repositories.ClienteRepository;
import edu.ifes.ci.si.les.scc.repositories.ComissaoVendaRepository;
import edu.ifes.ci.si.les.scc.repositories.FuncionarioRepository;
import edu.ifes.ci.si.les.scc.repositories.itemRevisaoRepository;
import edu.ifes.ci.si.les.scc.repositories.MotoRepository;
import edu.ifes.ci.si.les.scc.repositories.ProdutoRepository;
import edu.ifes.ci.si.les.scc.repositories.RealizaRecallRepository;
import edu.ifes.ci.si.les.scc.repositories.RecallRepository;
import edu.ifes.ci.si.les.scc.repositories.RevisaoRepository;
import edu.ifes.ci.si.les.scc.repositories.TaxaComissaoRepository;
import edu.ifes.ci.si.les.scc.repositories.TipoMotoRepository;
import edu.ifes.ci.si.les.scc.repositories.VendaRepository;


@Service
public class _DBService {

	@Autowired
    private ClienteRepository clienteRepository;
	
	@Autowired
    private ComissaoVendaRepository comissaoVendaRepository;
	
	@Autowired
    private FuncionarioRepository funcionarioRepository;
	
	@Autowired
    private itemRevisaoRepository itemrevisaoRepository;
	
	@Autowired
    private MotoRepository motoRepository;
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
	@Autowired
    private RealizaRecallRepository realizaRecallRepository;
	
	@Autowired
    private RecallRepository recallRepository;
	
	@Autowired
    private RevisaoRepository revisaoRepository;
	
	@Autowired
    private TaxaComissaoRepository taxaComissaoRepository;
	
	@Autowired
    private TipoMotoRepository tipoMotoRepository;
	
	@Autowired
    private VendaRepository vendaRepository;
	
	
	public void instantiateTestDatabase() throws ParseException, IOException {
			
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        
        TipoMoto tipoMoto1 = new TipoMoto(null, "Street");
        TipoMoto tipoMoto2 = new TipoMoto(null, "Adventure");
        TipoMoto tipoMoto3 = new TipoMoto(null, "Off road");
        TipoMoto tipoMoto4 = new TipoMoto(null, "Sport");
        TipoMoto tipoMoto5 = new TipoMoto(null, "Touring");
		
        TaxaComissao taxaComissao1 = new TaxaComissao(null, 5.00, 20000.00, 1, "Vendedor");
        TaxaComissao taxaComissao2 = new TaxaComissao(null, 7.00, 50000.00, 3, "Vendedor");
        
        Produto produto1 = new Produto(null, "Bateria", "Moura", 307.02, 3);
        Produto produto2 = new Produto(null, "Bateria", "Bosch", 149.00, 5);
        Produto produto3 = new Produto(null, "Óleo Lubrificante", "Yamalube", 30.00, 10);

        
        
//        tipoMotoRepository.saveAll(Arrays.asList(tipoMoto1, tipoMoto2, tipoMoto3, tipoMoto4, tipoMoto5));
        taxaComissaoRepository.saveAll(Arrays.asList(taxaComissao1, taxaComissao2));
//        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
   	}
}
