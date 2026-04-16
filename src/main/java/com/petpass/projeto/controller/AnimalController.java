package com.petpass.projeto.controller;

import com.petpass.projeto.model.Animal;
import com.petpass.projeto.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // cadastro: recebe o ID do Tutor na URL para não deixar o pet órfão
    @PostMapping("/usuarios/{tutorId}")
    public Animal realizarCadastro(@PathVariable Long tutorId, @RequestBody Animal novoAnimal) {
        return animalService.cadastrar(tutorId, novoAnimal);
    }

    // atualização do Animal
    @PatchMapping("/{id}")
    public Animal atualizar(@PathVariable Long id, @RequestBody Animal dados) {
        return animalService.atualizarAnimal(id, dados);
    }
}