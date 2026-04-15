package com.petpass.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petpass.projeto.model.Animal;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}