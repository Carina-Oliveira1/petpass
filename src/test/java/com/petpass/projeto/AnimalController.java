package com.petpass.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.petpass.projeto.service.AnimalService;
import org.springframework.web.bind.annotation.*;
import com.petpass.projeto.model.Animal;
import com.petpass.projeto.model.Usuario;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public Animal realizarCadastro(@RequestBody Animal novoAnimal) {        

        System.out.println("Nome do tutor: " + novoAnimal.getUsuario());
        System.out.println("Cadastrando animal: " + novoAnimal.getNome());
        System.out.println("RG: " + novoAnimal.getRg());
        System.out.println("Raça: " + novoAnimal.getRaca());
        System.out.println("Classificação: " + novoAnimal.getClassificacao());

        return animalService.cadastrar(novoAnimal);
    }

}