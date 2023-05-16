package br.ufma.sppg.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufma.sppg.Dto.Tecnica.TecnicaResponse;
import br.ufma.sppg.repo.TecnicaRepository;

public class TecnicaService implements ITecnica{

    @Autowired
    private TecnicaRepository tecnicaRepository;

    @Override
    public ArrayList<TecnicaResponse> obterTecnicasPPG(Integer id) {
        
    }

    @Override
    public ArrayList<TecnicaResponse> obterTecnicasDocente(Integer idDocente) {
       
    }
    
}
