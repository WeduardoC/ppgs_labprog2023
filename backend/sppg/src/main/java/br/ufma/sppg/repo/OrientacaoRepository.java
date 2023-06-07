package br.ufma.sppg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufma.sppg.model.Orientacao;

public interface OrientacaoRepository extends JpaRepository<Orientacao,Integer> {
    List<Orientacao> findAllById(Integer id);

    @Query("SELECT o FROM Orientacao o" +
            "JOIN Docente d " +
            "WHERE d.id = :idDocente " +
            "AND o.ano >= :anoInicio " +
            "AND o.ano <= :anoFim")
    List<Orientacao> obterOrientacoesDocenteEmIntervalo(Integer idDocente, Integer anoInicio, Integer anoFim);
}
