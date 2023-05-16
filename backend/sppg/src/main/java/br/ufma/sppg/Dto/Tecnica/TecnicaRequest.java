package br.ufma.sppg.Dto.Tecnica;

import lombok.Data;

@Data
public class TecnicaRequest {
    String tipo;
    String titulo;
    Integer ano;
    String financiadora;
    String outrasInformacoes;
    Integer qtdGrad;
    Integer qtdMestrado;
    Integer qtdDoutorado;
}
