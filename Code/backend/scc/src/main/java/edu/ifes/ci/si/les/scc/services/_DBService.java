package edu.ifes.ci.si.les.scc.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scc.model.Cliente;
import edu.ifes.ci.si.les.scc.model.ComissaoVenda;
import edu.ifes.ci.si.les.scc.model.Funcionario;
import edu.ifes.ci.si.les.scc.model.ItemRevisao;
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
		
        
        TaxaComissao taxaComissao1 = new TaxaComissao(null, 5.00, 40000.00, 1, "Vendedor");
        TaxaComissao taxaComissao2 = new TaxaComissao(null, 7.00, 50000.00, 3, "Vendedor");
        
        
        Produto produto1 = new Produto(null, "Bateria", "Moura", 307.02, 1);
        Produto produto2 = new Produto(null, "Bateria", "Bosch", 149.00, 5);
        Produto produto3 = new Produto(null, "Óleo Lubrificante", "Yamalube", 30.00, 10);
        
        
        Cliente cliente1 = new Cliente(null, "Ruan Miniguite", "111.111.111-11", "Ruanminiguite@gmail.com", "(28)99918-3529", "Espirito Santo", "29295-000", "Vargem alta", "Pombal de Cima", 0);
        Cliente cliente2 = new Cliente(null, "Pedro Miniguite", "222.222.222-22", "Pedrominiguite@gmail.com", "(28)99912-1292", "Espirito Santo", "29295-000", "Vargem alta", "São josé de fruteiras", 1);
        Cliente cliente3 = new Cliente(null, "Alberto Ricado", "017.268.037-98", "AlbertoRicado@gmail.com", "(27)99865-9856", "Espirito Santo", "95689-000", "Pedra Azul", "Águas Vermelhas", 0);
        
        
        Funcionario funcionario1 = new Funcionario (null, "Alberto Ricado", "017.268.037-98", "(27)99865-9856", sdf.parse("2000-04-01"), "Espirito Santo", "95689-000", "Pedra Azul", "Águas Vermelhas", "AlbertoR", "123456", "Vendedor", 2500.66, 1, sdf.parse("2015-01-01"));
	    Funcionario funcionario2 = new Funcionario (null, "Marcos Bravim", "968.569.787-45", "(27)99999-9786", sdf.parse("1998-10-12"), "Espirito Santo", "29500-000", "Cachoeiro de Itapemirim", "Agostinho Simonato", "MarcosB", "789123", "Vendedor", 1856.44, 0, sdf.parse("2018-01-01"));
	    Funcionario funcionario3 = new Funcionario (null, "Mariana Ribeiro", "896.146.195-78", "(28)97896-9789", sdf.parse("1989-07-07"), "Espirito Santo", "29300-000", " Cachoeiro de Itapemirim", "Santo Antonio", "MarianaR", "456321", "Gerente", 4500.50, 0, sdf.parse("2020-01-01"));
	    
	    
	    Moto moto1 = new Moto(null, "Biz 110i", "Honda", 2011, 2011, "Vermelha", "Gasolina", 109.01, "TL0001", 16000.00, "ABC1B34", tipoMoto1, cliente1);
        Moto moto2 = new Moto(null, "XRE 300", "Honda", 2021, 2021, "Preta", "Gasolina", 291.6, "TL0035", 22000.00, "DGT1B34", tipoMoto2, cliente2);
        Moto moto3 = new Moto(null, "Bross 160", "Honda", 2020, 2021, "Branca", "Gasolina", 159.01, "TL0007", 20000.00, "ABD1B33", tipoMoto1, null);
        Moto moto4 = new Moto(null, "Biz 110i", "Honda", 2014, 2014, "Vermelha", "Gasolina", 109.01, "TL0002", 16000.00, "ABC1B34", tipoMoto1, cliente1);
        Moto moto5 = new Moto(null, "XRE 300", "Honda", 2015, 2015, "Preta", "Gasolina", 291.6, "TL0004", 22000.00, "DGT1B34", tipoMoto2, cliente3);
        Moto moto6 = new Moto(null, "Bross 160", "Honda", 2010, 2010, "Branca", "Gasolina", 159.01, "TL0005", 20000.00, "ABD1B33", tipoMoto1, cliente3);

        
        Venda venda1 = new Venda(null, sdf.parse("2022-03-07"), 16000.00, true, 150.00, cliente1, funcionario2, moto1);
        Venda venda2 = new Venda(null, sdf.parse("2022-03-13"), 22000.00, false, 100.00, cliente2, funcionario3, moto2);

        
        ComissaoVenda comissaoVenda1 = new ComissaoVenda(null, sdf.parse("01-03-2022"), sdf.parse("30-03-2022"), 16000.00, false, taxaComissao1, venda1);
        ComissaoVenda comissaoVenda2 = new ComissaoVenda(null, sdf.parse("01-03-2022"), sdf.parse("30-03-2022"), 22000.00, false, taxaComissao1, venda2);
        
        
        Recall recall1 = new Recall(null, "Recall Corrente de Comando", sdf.parse("2022-05-05"),"TL0001", "TL0100", 2016, sdf.parse("2022-05-20"), sdf.parse("2022-06-20"), funcionario1);
        Recall recall2 = new Recall(null, "Recall Velas", sdf.parse("2022-05-15"),"HR0001", "HR0100", 2019, sdf.parse("2022-03-01"), sdf.parse("2022-04-01"), funcionario2);
        
        
        RealizaRecall realizaRecall= new RealizaRecall(null, "2022-05-21", 0, moto1, recall1);
        RealizaRecall realizaRecal2= new RealizaRecall(null, "2022-05-21", 0, moto2, recall1);
        RealizaRecall realizaRecal3= new RealizaRecall(null, "2022-05-21", 0, moto3, recall1);
        RealizaRecall realizaRecal4= new RealizaRecall(null, "2022-05-21", 0, moto4, recall1);
        RealizaRecall realizaRecal5= new RealizaRecall(null, "2022-05-20", 0, moto5, recall1);

        Revisao revisao1 = new Revisao(null, sdf.parse("2022-05-01"), 795.25, funcionario1, moto1);
        Revisao revisao2 = new Revisao(null, sdf.parse("2022-05-03"), 356.00, funcionario1, moto2);
        
        
        ItemRevisao ItemRevisao1 = new ItemRevisao(revisao1, produto1, 307.02, 1);
        ItemRevisao ItemRevisao2 = new ItemRevisao(revisao2, produto3, 30.00, 1);
        
        revisao1.setItens(Arrays.asList(ItemRevisao1));
        revisao2.setItens(Arrays.asList(ItemRevisao2));
        
        tipoMotoRepository.saveAll(Arrays.asList(tipoMoto1, tipoMoto2, tipoMoto3, tipoMoto4, tipoMoto5));
        taxaComissaoRepository.saveAll(Arrays.asList(taxaComissao1, taxaComissao2));
        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
        funcionarioRepository.saveAll(Arrays.asList(funcionario1, funcionario2, funcionario3));
        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));
        motoRepository.saveAll(Arrays.asList(moto1, moto2, moto3, moto4, moto5, moto6));
        vendaRepository.saveAll(Arrays.asList(venda1, venda2));
        comissaoVendaRepository.saveAll(Arrays.asList(comissaoVenda1, comissaoVenda2));
        recallRepository.saveAll(Arrays.asList(recall1, recall2));
        realizaRecallRepository.saveAll(Arrays.asList(realizaRecall, realizaRecal2, realizaRecal3, realizaRecal4, realizaRecal5));
        revisaoRepository.saveAll(Arrays.asList(revisao1, revisao2));
	}
}
