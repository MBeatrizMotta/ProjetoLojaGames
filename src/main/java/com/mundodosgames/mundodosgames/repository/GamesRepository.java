package com.mundodosgames.mundodosgames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mundodosgames.mundodosgames.model.GamesModel;

@Repository
public interface GamesRepository extends JpaRepository<GamesModel, Long>{
	public List<GamesModel> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
