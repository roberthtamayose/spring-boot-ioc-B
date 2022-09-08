package com.rtamayose.cursospringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rtamayose.cursospringboot.domain.Cliente;
import com.rtamayose.cursospringboot.repositories.ClienteRepository;
import com.rtamayose.cursospringboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Obejeto não encontrado id: " +  id + ", Tipo:" + Cliente.class.getName()));
	} 
}
