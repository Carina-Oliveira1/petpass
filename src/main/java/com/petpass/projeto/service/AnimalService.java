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

    // 📌 CADASTRAR ANIMAL
    public Animal cadastrar(Long tutorId, Animal animal) {
        
        // 1. Busca o tutor. Se não achar, trava o cadastro.
        Usuario tutor = usuarioRepository.findById(tutorId)
            .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID informado!"));

        // 2. Validações do Membro 2 corrigidas
        if(animal == null || 
            animal.getNome() == null || animal.getNome().trim().isEmpty() || 
            animal.getRg() == null || animal.getRg().trim().isEmpty() ||
            animal.getRaca() == null || animal.getRaca().trim().isEmpty() ||
            animal.getDataNascimento() == null) {
                throw new CamposObrigatoriosException("Nome, RG, Raça e Data de Nascimento são obrigatórios!");
        }
        
        // 3. Regra de negócio nativa
        animal.calcularIdadeEClassificacao();

        // 4. Salva o animal
        Animal animalSalvo = animalRepository.save(animal);

        // 5. Vincula o animal à lista do tutor e salva o tutor
        tutor.adicionarAnimal(animalSalvo);
        usuarioRepository.save(tutor);

        return animalSalvo;
    }

    // 📌 ATUALIZAR ANIMAL
    public Animal atualizarAnimal(Long id, Animal dados) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        animal.setEhCastrado(dados.isEhCastrado());
        animal.setTemMicroship(dados.isTemMicroship());
        animal.setHistoricoVacina(dados.getHistoricoVacina());

        // Gatilho obrigatório da data de nascimento
        if (dados.getDataNascimento() != null &&
            !Objects.equals(dados.getDataNascimento(), animal.getDataNascimento())) {

            animal.setDataNascimento(dados.getDataNascimento());
            animal.calcularIdadeEClassificacao();
        }

        return animalRepository.save(animal);
    }
}