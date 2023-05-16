package br.ufma.sppg.services;

import java.util.ArrayList;

import br.ufma.sppg.Dto.Tecnica.TecnicaResponse;

public interface ITecnica {
    public ArrayList<TecnicaResponse> obterTecnicasPPG(Integer id);
    public ArrayList<TecnicaResponse> obterTecnicasDocente(Integer idDocente);
}
