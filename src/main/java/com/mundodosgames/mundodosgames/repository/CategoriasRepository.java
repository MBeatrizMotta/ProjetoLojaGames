package com.mundodosgames.mundodosgames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mundodosgames.mundodosgames.model.CategoriasModel;

@Repository
public interface CategoriasRepository extends JpaRepository<CategoriasModel, Long> {
	public List<CategoriasModel> findAllByPlataformaContainingIgnoreCase(@Param("plataforma") String plataforma);
	

}
