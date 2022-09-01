package com.rtamayose.cursospringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rtamayose.cursospringboot.domain.Categoria;
import com.rtamayose.cursospringboot.repositories.CategoriaRepository;
import com.rtamayose.cursospringboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Obejeto não encontrado id: " +  id + ", Tipo:" + Categoria.class.getName()));
	} 
}
