package br.ufma.sppg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@Rest Controller
@RequestMapping("/api/programa")
public class ProgramaController {
    
    @Autowired
    ProgramaService programaService;
}
