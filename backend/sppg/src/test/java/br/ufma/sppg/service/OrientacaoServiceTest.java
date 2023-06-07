package br.ufma.sppg.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import br.ufma.sppg.model.Docente;
import br.ufma.sppg.model.Orientacao;
import br.ufma.sppg.model.Programa;
import br.ufma.sppg.model.Tecnica;
import br.ufma.sppg.repo.DocenteRepository;
import br.ufma.sppg.repo.OrientacaoRepository;
import br.ufma.sppg.repo.ProgramaRepository;
import br.ufma.sppg.repo.ProducaoRepository;
import br.ufma.sppg.repo.TecnicaRepository;
import br.ufma.sppg.services.exceptions.ServicoRunTimeException;

@SpringBootTest
public class OrientacaoServiceTest {
    
    @Autowired
    private OrientacaoRepository orientacaoRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private TecnicaRepository tecnicaRepository;

    @Autowired
    private ProducaoRepository producaoRepository;

    @Test
    public void deveInformarIntervaloComoDoisInteiros(Integer anoInicio, Integer anoFim){
        Assertions.assertNotNull(anoInicio);
        Assertions.assertNotNull(anoFim);
        Assertions.assertInstanceOf(Integer.class, anoInicio);
        Assertions.assertInstanceOf(Integer.class, anoFim);
    }

    @Test
    public void deveObterOrientacoesDocenteEmIntervalo(Integer anoInicio, Integer anoFim){
        Docente docente = Docente.builder()
        .nome("Geraldo Braz Junior")
        .lattes("123").dataAtualizacao(new SimpleDateFormat("dd/MM/yyyy"))
        .build();

        Orientacao orientacao1 = Orientacao.builder().tipo("A")
        .discente("Eduardo").ano(anoFim).modalidade("modalidade 1")
        .instituicao("UFMA").curso("Ciência da Computação")
        .status("Aprovado").build();

        Orientacao orientacao2 = Orientacao.builder().tipo("B")
        .discente("Ronaldo").ano(anoInicio).modalidade("modalidade 2")
        .instituicao("UFMA").curso("Ciência da Computação")
        .status("Aprovado").build();

        Docente docSalvo = docenteRepository.save(docente);
        Orientacao oriSalvo1 = orientacaoRepository.save(orientacao1);
        Orientacao oriSalvo2 = orientacaoRepository.save(orientacao2);

        List<Orientacao> orientacoes = new ArrayList<>();
        orientacoes.add(oriSalvo1);
        orientacoes.add(oriSalvo2);

        List<Orientacao> orientacoesQuery = orientacaoService.obterOrientacoesDocenteEmIntervalo(docSalvo.getId(),anoInicio,anoFim);

        //verificação
        Assertions.assertNotNull(orientacoesQuery);
        Assertions.assertEquals(2, orientacoesQuery.size());
        Assertions.assertEquals(oriSalvo1.getId(), orientacoesQuery.get(0).getId());
        Assertions.assertEquals(oriSalvo2.getId(), orientacoesQuery.get(1).getId());
        
    }

    @Test
    public void deveObterOrientacoesProgramaEmIntervalo(Integer anoInicio, Integer anoFim){
        Programa programa = Programa.builder()
        .nome("Programa de Pós-Graduação em Engenharia de Eletricidade")
        .build();

        Orientacao orientacao1 = Orientacao.builder().tipo("A")
        .discente("Eduardo").ano(anoFim).modalidade("modalidade 1")
        .instituicao("UFMA").curso("Ciência da Computação")
        .status("Aprovado").build();

        Orientacao orientacao2 = Orientacao.builder().tipo("B")
        .discente("Ronaldo").ano(anoInicio).modalidade("modalidade 2")
        .instituicao("UFMA").curso("Ciência da Computação")
        .status("Aprovado").build();

        Programa progSalvo = programaRepository.save(programa);
        Orientacao oriSalvo1 = orientacaoRepository.save(orientacao1);
        Orientacao oriSalvo2 = orientacaoRepository.save(orientacao2);

        List<Orientacao> orientacoes = new ArrayList<>();
        orientacoes.add(oriSalvo1);
        orientacoes.add(oriSalvo2);

        List<Orientacao> orientacoesQuery = orientacaoRepository.obterOrientacoesProgramaEmIntervalo(progSalvo.getId(), anoInicio, anoFim);

        //verificação
        Assertions.assertNotNull(orientacoesQuery);
        Assertions.assertEquals(2, orientacoesQuery.size());
        Assertions.assertEquals(oriSalvo1.getId(), orientacoesQuery.get(0).getId());
        Assertions.assertEquals(oriSalvo2.getId(), orientacoesQuery.get(1).getId());
        
    }

    @Test
    public void deveAssociarTecnicaOrientacao(){
        Tecnica tecnica = Tecnica.builder()
        .tipo("A").titulo("Técnica X").ano(2022)
        .financiadora("B").outrasInformacoes("algo")
        .qtdGrad(2).qtdMestrado(3).qtdDoutorado(4)
        .build();

        Orientacao orientacao = Orientacao.builder().tipo("A")
        .discente("Eduardo").ano(2023).modalidade("modalidade 1")
        .instituicao("UFMA").curso("Ciência da Computação")
        .status("Aprovado").build();


        Tecnica tecSalvo = tecnicaRepository.save(tecnica);
        Orientacao oriSalvo = orientacaoRepository.save(orientacao);

        orientacaoService.associarTecnicaOrientacao(oriSalvo.getId(),tecSalvo.getId());

        //verificação
        Assertions.assertNotNull(oriSalvo);
        Assertions.assertEquals(oriSalvo.getTecnicas().get(0).getFinanciadora(), tecSalvo.getFinanciadora());
        
    }

    @Test
    public void deveAssociarProducaoOrientacao(){
        Producao producao = Producao.builder().tipo("A")
        .issnOuSigla("issn").ano(2023).nomeLocal("nome")
        .titulo("titulo").qualis("A").percentileOuH5(1.0)
        .qtdGrad(7).qtdMestrado(4).qtdDoutorado(1).build();

        Orientacao orientacao = Orientacao.builder().tipo("A")
        .discente("Eduardo").ano(2023).modalidade("modalidade 1")
        .instituicao("UFMA").curso("Ciência da Computação")
        .status("Aprovado").build();


        Producao prodSalvo = producaoRepository.save(producao);
        Orientacao oriSalvo = orientacaoRepository.save(orientacao);

        orientacaoService.associarProducaoOrientacao(oriSalvo.getId(),prodSalvo.getId());

        //verificação
        Assertions.assertNotNull(oriSalvo);
        Assertions.assertEquals(oriSalvo.getProducoes().get(0).getPercentileOuH5(), prodSalvo.getPercentileOuH5());
        
    }

    @Test
    public void naoDeveAssociarOrientacoesRepetidasTecnica(){
        Orientacao orientacao = Orientacao.builder().tipo("A")
        .discente("Eduardo").ano(2023).modalidade("modalidade 1")
        .instituicao("UFMA").curso("Ciência da Computação")
        .status("Aprovado").build();

        Tecnica tecnica = Tecnica.builder()
        .tipo("A").titulo("Técnica X").ano(2022)
        .financiadora("B").outrasInformacoes("algo")
        .qtdGrad(2).qtdMestrado(3).qtdDoutorado(4)
        .build();

        Tecnica tecSalvo = tecnicaRepository.save(tecnica);
        Orientacao oriSalvo = orientacaoRepository.save(orientacao);

        List<Tecnica> tecnicas = new ArrayList<>();
        tecnicas.add(tecSalvo);

        oriSalvo.setTecnicas(tecnicas);
        Orientacao oriSalvo2 = orientacaoRepository.save(oriSalvo);

        //verificação
        if(Assertions.assertEquals(oriSalvo.getId(), oriSalvo2.getId())){
            throw new ServicoRunTimeException("não deve associar orientações repetidas a uma mesma técnica");
        }
    }

    @Test
    public void naoDeveAssociarOrientacoesRepetidasProducao(){
        Orientacao orientacao = Orientacao.builder().tipo("A")
        .discente("Eduardo").ano(2023).modalidade("modalidade 1")
        .instituicao("UFMA").curso("Ciência da Computação")
        .status("Aprovado").build();

        Producao producao = Producao.builder().tipo("A")
        .issnOuSigla("issn").ano(2023).nomeLocal("nome")
        .titulo("titulo").qualis("A").percentileOuH5(1.0)
        .qtdGrad(7).qtdMestrado(4).qtdDoutorado(1).build();

        Producao prodSalvo = producaoRepository.save(producao);
        Orientacao oriSalvo = orientacaoRepository.save(orientacao);

        List<Producao> producoes = new ArrayList<>();
        producoes.add(prodSalvo);

        oriSalvo.setProducoes(producoes);
        Orientacao oriSalvo2 = orientacaoRepository.save(oriSalvo);

        //verificação
        if(Assertions.assertEquals(oriSalvo.getId(), oriSalvo2.getId())){
            throw new ServicoRunTimeException("não deve associar orientações repetidas a uma mesma produção");
        }

    }
}
