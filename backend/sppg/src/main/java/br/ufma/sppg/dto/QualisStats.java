package br.ufma.sppg.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QualisStats {
    int producoes;
    int orientacoes;
    int tecnicas;
}
