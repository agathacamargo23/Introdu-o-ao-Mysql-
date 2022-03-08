package org.generation.lojagames.Lojagames.Repository;

import java.util.List;

import org.generation.lojagames.Lojagames.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends  JpaRepository<CategoriaModel, Long> {
	public List<CategoriaModel> findAllByGeneroContainingIgnoreCase(String  genero);

}
