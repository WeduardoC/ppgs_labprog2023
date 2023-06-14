package br.ufma.sppg.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import br.ufma.sppg.dto.IndiceQualis;
import br.ufma.sppg.dto.QualisStats;
import br.ufma.sppg.dto.QualisSumario;
import br.ufma.sppg.dto.Indice;
import br.ufma.sppg.model.Producao;
import br.ufma.sppg.model.Orientacao;
import br.ufma.sppg.model.Tecnica;
import br.ufma.sppg.services.exceptions.ServicoRuntimeException;

@Rest Controller
@RequestMapping("/api/qualis")
public class ProgramaQualisController {
    
    @Autowired
    ProgramaService programaService;

    @GetMapping("{idProgroma}/{anoInico}/{anoFim}")
    public ResponseEntity obterIndiceCapesEmIntervalo(@PathVariable Integer idPrograma, @PathVariable Integer anoInicio, @PathVariable Integer anoFim){
        Indice indice;
        List<Producao> producoes;
        
        try{
            indice = ProgramaService.obterProducaoIndices(idPrograma, anoInicio, anoFim);
            producoes = ProgramaService.obterProducoes(idPrograma, anoInicio, anoFim);
        }catch(RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        IndiceQualis qualis = IndiceQualis.builder().indice(indice).producoes(producoes).build();
        return new ResponseEntity(qualis, HttpStatus.OK);
    }

    @GetMapping("{idPrograma}/{qualis}/{anoInico}/{anoFim}")
    public ResponseEntity obterPublicacoesPorQualisEmIntervalo(@PathVariable Integer idPrograma, @PathVariable String qualis, @PathVariable Integer anoInicio, @PathVariable Integer anoFim){
        QualisSumario qualis_sumario = QualisSumario.builder().build();
        qualis_sumario.setQtd(0);
        List<Producao> producoes;
        List<Producao> prod_filtro = new ArrayList<Producao>();

        try{
            producoes = programaService.obterProducoes(idPrograma,anoInicio,anoFim);
            for(Producao prod : producoes){
                if (prod.getQualis().equals(qualis)){
                    qualis_sumario.setQtd(qualis_sumario.getQtd()+1);
                    prod_filtro.add(prod);
                }
            }
            qualis_sumario.setProducoes(prod_filtro);
        }catch(RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return new ResponseEntity(qualis_sumario.getQtd(), HttpStatus.OK);
    }

    @GetMapping("{idPrograma}/{anoInico}/{anoFim}")
    public ResponseEntity obterEstatisticasProducaoEmIntervalo(@PathVariable Integer idPrograma, @PathVariable Integer anoInicio, @PathVariable Integer anoFim){
        QualisStats qualis_stats;

        try{
            int producoes = programaService.obterProducoes(idPrograma, anoInicio, anoFim).size();
            int orientacoes = programaService.obterOrientacoes(idPrograma, anoInicio, anoFim).size();
            int tecnicas = programaService.obterTecnicas(idPrograma, anoInicio, anoFim).size();
            qualis_stats = QualisStats.builder().producoes(producoes).orientacoes(orientacoes).tecnicas(tecnicas).build();
        }catch(RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return new ResponseEntity(qualis_stats, HttpStatus.OK);
    }
}
