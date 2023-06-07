package br.ufma.sppg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufma.sppg.model.Docente;
import br.ufma.sppg.model.Orientacao;
import br.ufma.sppg.model.Programa;
import br.ufma.sppg.model.Tecnica;
import br.ufma.sppg.repo.DocenteRepository;
import br.ufma.sppg.repo.ProgramaRepository;
import br.ufma.sppg.repo.TecnicaRepository;
import br.ufma.sppg.services.exceptions.ServicoRunTimeException;

public class TecnicaService{

    @Autowired
    private TecnicaRepository tecnicaRepository;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private DocenteRepository docenteRepository;


    public Optional<List<Tecnica>> obterTecnicasPPG(Integer idPrograma) {

        Optional<Programa> programa = programaRepository.findById(idPrograma);
        if (programa.isPresent()) {
            return tecnicaRepository.obterTecnicasPPG(idPrograma);
        }

        throw new ServicoRunTimeException("O programa informado não foi encontrado");

    }

    public Optional<List<Tecnica>> obterTecnicasPPGEmIntervalo(Integer idPrograma, Integer anoInicio, Integer anoFim) {

        Optional<Programa> programa = programaRepository.findById(idPrograma);
        if (programa.isPresent()) {
            if (anoInicio > anoFim) {
                Integer temp = anoInicio;
                anoInicio = anoFim;
                anoInicio = temp;
            }
            return tecnicaRepository.obterTecnicasPPGPorPeriodo(idPrograma, anoInicio, anoFim);
        }

        throw new ServicoRunTimeException("O programa informado não foi encontrado");

    }

    public Optional<List<Tecnica>> obterTecnicasDocente(Integer idDocente) {

        Optional<Docente> docente = docenteRepository.findById(idDocente);

        if (docente.isPresent()) {
            return tecnicaRepository.obterTecnicasDocente(idDocente);
        }

        throw new ServicoRunTimeException("O docente não foi encontrado");
    }

    public Optional<List<Tecnica>> obterTecnicasDocenteEmIntervalo(Integer idDocente, Integer anoInicio, Integer anoFim) {
        Optional<Docente> docente = docenteRepository.findById(idDocente);

        if (docente.isPresent()) {

            if (anoInicio > anoFim) {
                Integer temp = anoInicio;
                anoInicio = anoFim;
                anoInicio = temp;
            }

            return tecnicaRepository.obterTecnicasDocenteEmIntervalo(idDocente, anoInicio, anoFim);
        }

        throw new ServicoRunTimeException("O docente não foi encontrado");
    }

    public Tecnica informarEstatisticasTecnica(Integer idTecnica, Integer qtd_grad, Integer qtd_mestrado, Integer qtd_doutorado) {

    Optional<Tecnica> tecnica = tecnicaRepository.findById(idTecnica);

    if (tecnica.isPresent()) {
      Tecnica tecnica2 = tecnica.get();

      tecnica2.setQtdGrad(qtd_grad);
      tecnica2.setQtdMestrado(qtd_mestrado);
      tecnica2.setQtdDoutorado(qtd_doutorado);

      return tecnicaRepository.save(tecnica2);
    }

    throw new ServicoRunTimeException("A técnica não foi encontrada");
  }

    public Optional<List<Orientacao>> obterOrientacoesTecnica(Integer idTecnica) {
        Optional<Tecnica> tecnica = tecnicaRepository.findById(idTecnica);

        if (tecnica.isPresent()) {
            return tecnicaRepository.obterOrientacoesTecnica(idTecnica);
        }

        throw new ServicoRunTimeException("A técnica não foi encontrada");
    }

    public Optional<List<Orientacao>> obterOrientacoesTecnicaEmIntervalo(Integer idTecnica, Integer anoInicio, Integer anoFim) {
        Optional<Tecnica> tecnica = tecnicaRepository.findById(idTecnica);

        if (tecnica.isPresent()) {

            if (anoInicio > anoFim) {
                Integer temp = anoInicio;
                anoInicio = anoFim;
                anoInicio = temp;
            }

            return tecnicaRepository.obterOrientacoesTecnicaEmIntervalo(idTecnica, anoInicio, anoFim);
        }

        throw new ServicoRunTimeException("A técnica não foi encontrada");
    }
    
}
