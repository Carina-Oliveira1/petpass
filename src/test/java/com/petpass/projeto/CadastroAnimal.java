package com.petpass.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.petpass.projeto.service.AnimalService;
import org.springframework.web.bind.annotation.*;
import com.petpass.projeto.model.Animal;

@RestController
@RequestMapping("/animais")
public class CadastroAnimal {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public Animal realizarCadastro(@RequestBody Animal novoAnimal) {
        novoAnimal.calcularIdadeEClassificacao();

        System.out.println("Cadastrando animal: " + novoAnimal.getNome());
        System.out.println("RG: " + novoAnimal.getRg());
        System.out.println("Raça: " + novoAnimal.getRaca());
        System.out.println("Classificação: " + novoAnimal.getClassificacao());

        return animalService.cadastrar(novoAnimal);
    }

}