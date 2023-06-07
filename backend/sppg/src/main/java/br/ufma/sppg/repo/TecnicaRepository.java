package br.ufma.sppg.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufma.sppg.model.Orientacao;
import br.ufma.sppg.model.Tecnica;

public interface TecnicaRepository 
    extends JpaRepository<Tecnica, Integer> {

    List<Tecnica> findAllById(Integer id);

    @Query("SELECT t FROM Tecnica t " +
            "JOIN Docente d " +
            "JOIN Programa p " +
            "WHERE p.id = :idPrograma")
    Optional<List<Tecnica>> obterTecnicasPPG(Integer idPrograma);

    @Query("SELECT t FROM Tecnica t " +
            "JOIN Docente d " +
            "JOIN Programa p " +
            "WHERE p.id = :idPrograma " +
            "AND t.ano >= :anoInicio " +
            "AND t.ano <= :anoFim")
    Optional<List<Tecnica>> obterTecnicasPPGPorPeriodo(Integer idPrograma, Integer anoInicio, Integer anoFim);

    @Query("SELECT t FROM Tecnica t " +
            "JOIN Docente d " +
            "WHERE d.id = :idDocente ")
    Optional<List<Tecnica>> obterTecnicasDocente(Integer idDocente);

    @Query("SELECT t FROM Tecnica t " +
            "JOIN Docente d " +
            "WHERE d.id = :idDocente " +
            "AND t.ano >= :anoInicio " +
            "AND t.ano <= :anoFim")
    Optional<List<Tecnica>> obterTecnicasDocenteEmIntervalo(Integer idDocente, Integer anoInicio, Integer anoFim);

    @Query("SELECT o FROM Orientacao o " +
            "JOIN Tecnica t " +
            "WHERE t.id = :idTecnica ")
    Optional<List<Orientacao>> obterOrientacoesTecnica(Integer idTecnica);

    @Query("SELECT o FROM Orientacao o " +
            "JOIN Tecnica t " +
            "WHERE t.id = :idTecnica " +
            "AND o.ano >= :anoInicio " +
            "AND o.ano <= :anoFim")
    Optional<List<Orientacao>> obterOrientacoesTecnicaEmIntervalo(Integer idTecnica, Integer anoInicio, Integer anoFim);

}
