package br.ufma.sppg.model;

import java.util.Date;
import java.util.HashSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.ufma.sppg.repo.DocenteRepository;
import br.ufma.sppg.repo.ProgramaRepository;

@SpringBootTest
public class ProgramaTest {

  @Autowired
  private ProgramaRepository programaRepository;

  @Autowired
  private DocenteRepository docenteRepository;

  @Test
  public void deveSalvarPrograma() {
    Programa novoPrograma = Programa.builder()
        .nome("Programa de Pós-Graduação em Engenharia de Eletricidade")
        .build();

    Programa programaSalvo = programaRepository.save(novoPrograma);

    Assertions.assertNotNull(programaSalvo);

  }
  
  @Test
  public void deveSalvarProgramaComDocente() {
    Programa novoPrograma = Programa.builder()
        .nome("Programa de Pós-Graduação em Engenharia de Eletricidade")
        .build();
    Docente novoDocente = Docente.builder()
        .nome("Geraldo Braz Junior")
        .lattes("123").dataAtualizacao(new SimpleDateFormat("dd/MM/yyyy"))
        .build();

    Programa programaSalvo = programaRepository.save(novoPrograma);
    Docente docenteSalvo = docenteRepository.save(novoDocente);
    
    List<Docente> docentes = new ArrayList<Docente>();
    docentes.add(docenteSalvo);
    programaSalvo.setDocentes(docentes);
    Programa programaSalvo2 = programaRepository.save(programaSalvo);

    Assertions.assertNotNull(programaSalvo2);
    Assertions.assertEquals(programaSalvo2.getDocentes().size(), 1);

  }
  
  @Test
  public void deveImpedirRemoverProgramaComDocente() {
    Programa novoPrograma = Programa.builder()
        .nome("Programa de Pós-Graduação em Engenharia de Eletricidade")
        .build();
    Docente novoDocente = Docente.builder()
        .nome("Geraldo Braz Junior")
        .lattes("123").dataAtualizacao(new SimpleDateFormat("dd/MM/yyyy"))
        .build();

    Programa programaSalvo = programaRepository.save(novoPrograma);
    Docente docenteSalvo = docenteRepository.save(novoDocente);
    List<Docente> docentes = new ArrayList<Docente>();
    docentes.add(docenteSalvo);
    programaSalvo.setDocentes(docentes);
    Programa programaSalvo2 = programaRepository.save(programaSalvo);
    Assertions.assertNotNull(programaSalvo2);

    try {
      programaRepository.delete(programaSalvo2);
      Assertions.fail("Não deve deletar programa com docente");
    } catch (DataIntegrityViolationException e) {
      // TODO: handle exception
    }

    
    Optional<Programa> prog = programaRepository.findById(programaSalvo2.getId());
    Assertions.assertNotNull(prog.isPresent());
    List<Docente> doc = docenteRepository.findAllById(Collections.singleton(docenteSalvo.getId()));
    Assertions.assertFalse(doc.isEmpty());


  }

  
}
