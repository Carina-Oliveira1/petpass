package com.petpass.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petpass.projeto.model.Animal;
import com.petpass.projeto.repository.AnimalRepository;
import com.petpass.projeto.expections.CamposObrigatoriosException;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;  

    public Animal cadastrar(Animal animal) {
        try {
            if(animal == null || 
                animal.getnome() == null || animal.getnome().trim().isEmpty() || 
                animal.getRg() == null || animal.getRg().trim().isEmpty() ||
                animal.getRaca() == null || animal.getRaca().trim().isEmpty() ||
                animal.getDataNascimento() == null) {
                    throw new CamposObrigatoriosException("Nome, RG, Raça e Data de Nascimento são obrigatórios!");
                }                
                animal.calcularIdadeEClassificacao();

                return animalRepository.save(animal);
        } catch (CamposObrigatoriosException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro interno ao cadastrar o pet.");
        }
    }
}