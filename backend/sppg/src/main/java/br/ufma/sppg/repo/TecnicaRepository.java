package br.ufma.sppg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.sppg.model.Tecnica;

public interface TecnicaRepository 
    extends JpaRepository<Tecnica, Integer> {
        List<Tecnica> findAllById(Integer id);
}
