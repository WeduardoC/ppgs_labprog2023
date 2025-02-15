package br.ufma.sppg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producao")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producao")
    Integer id;

    @Column(name="tipo")    
    String tipo;

    @Column(name ="issn_ou_sigla")
    String issnOuSigla;

    @Column(name="nome_local")
    String nomeLocal;

    @Column(name= "titulo")
    String titulo;

    @Column(name="ano")
    Integer ano;

    @Column(name = "qualis")
    String qualis;

    @Column(name="percentile_ou_h5")
    float percentileOuH5;

    @Column(name="qtd_grad")
    Integer qtdGrad;

    @Column(name="qtd_mestrado")
    Integer qtdMestrado;

    @Column(name="qtd_doutorado")
    Integer qtdDoutorado;
}
