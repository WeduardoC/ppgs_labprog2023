package br.ufma.sppg.dto;

import java.util.List;

import br.ufma.sppg.model.Producao;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QualisSumario {
    private Integer qtd;
    private List<Producao> producoes;
}
