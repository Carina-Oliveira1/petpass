package com.petpass.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petpass.projeto.model.Animal;
import com.petpass.projeto.model.Usuario;
import com.petpass.projeto.repository.AnimalRepository;
import com.petpass.projeto.repository.UsuarioRepository;
import com.petpass.projeto.expections.CamposObrigatoriosException;
import java.util.Objects;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;  

    @Autowired
    private UsuarioRepository usuarioRepository; // Precisamos disso para achar o dono

    // cadastrar animal
    public Animal cadastrar(Long tutorId, Animal animal) {
        
        // busca o tutor. de não achar, trava o cadastro.
        Usuario tutor = usuarioRepository.findById(tutorId)
            .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID informado!"));

        if(animal == null || 
            animal.getNome() == null || animal.getNome().trim().isEmpty() || 
            animal.getRg() == null || animal.getRg().trim().isEmpty() ||
            animal.getRaca() == null || animal.getRaca().trim().isEmpty() ||
            animal.getDataNascimento() == null) {
                throw new CamposObrigatoriosException("Nome, RG, Raça e Data de Nascimento são obrigatórios!");
        }
        
        animal.calcularIdadeEClassificacao();

        // salva o animal
        Animal animalSalvo = animalRepository.save(animal);

        // vincula o animal à lista do tutor e salva o tutor
        tutor.adicionarAnimal(animalSalvo);
        usuarioRepository.save(tutor);

        return animalSalvo;
    }

    // atualizar animal
    public Animal atualizarAnimal(Long id, Animal dados) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        animal.setEhCastrado(dados.isEhCastrado());
        animal.setTemMicroship(dados.isTemMicroship());
        animal.setHistoricoVacina(dados.getHistoricoVacina());

        // gatilho obrigatório da data de nascimento
        if (dados.getDataNascimento() != null &&
            !Objects.equals(dados.getDataNascimento(), animal.getDataNascimento())) {

            animal.setDataNascimento(dados.getDataNascimento());
            animal.calcularIdadeEClassificacao();
        }

        return animalRepository.save(animal);
    }
}