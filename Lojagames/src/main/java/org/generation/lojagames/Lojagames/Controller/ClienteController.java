package org.generation.lojagames.Lojagames.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.lojagames.Lojagames.Repository.ClienteRepository;
import org.generation.lojagames.Lojagames.Service.ClienteService;
import org.generation.lojagames.Lojagames.model.ClienteLogin;
import org.generation.lojagames.Lojagames.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/all")
	public ResponseEntity <List<ClienteModel>> getAll(){
		return ResponseEntity.ok(clienteRepository.findAll());
	}

	@PostMapping("/logar")
	public ResponseEntity<ClienteLogin> login(@RequestBody Optional<ClienteLogin> clienteLogin){
		
		return clienteService.autentificarUsuario(clienteLogin)
			.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<ClienteModel> postCliente(@Valid @RequestBody ClienteModel cliente){
		
		return clienteService.cadastrarUsuario(cliente)
			.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
}
