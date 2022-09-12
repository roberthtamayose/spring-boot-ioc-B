package com.rtamayose.cursospringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rtamayose.cursospringboot.domain.Pedido;
import com.rtamayose.cursospringboot.repositories.PedidoRepository;
import com.rtamayose.cursospringboot.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Obejeto não encontrado id: " +  id + ", Tipo:" + Pedido.class.getName()));
	} 
}
