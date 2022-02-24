package org.generation.lojagames.Lojagames.Repository;

import java.util.List;


import org.generation.lojagames.Lojagames.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
	public List <ProdutoModel>findAllByDescricaoCategoriaContainingIgnoreCase(String descricaoCategoria);

}