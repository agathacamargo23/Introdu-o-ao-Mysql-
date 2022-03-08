package org.generation.lojagames.Lojagames.Repository;

import java.util.Optional;

import org.generation.lojagames.Lojagames.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
	public Optional<ClienteModel> findByUsuario(String usuario);

}
