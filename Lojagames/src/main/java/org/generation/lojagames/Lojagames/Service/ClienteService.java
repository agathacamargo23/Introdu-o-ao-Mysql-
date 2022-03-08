package org.generation.lojagames.Lojagames.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.generation.lojagames.Lojagames.Repository.ClienteRepository;
import org.generation.lojagames.Lojagames.model.ClienteLogin;
import org.generation.lojagames.Lojagames.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
public Optional<ClienteModel> cadastrarUsuario(ClienteModel cliente){
		
		if (clienteRepository.findByUsuario(cliente.getUsuario()).isPresent())
			return Optional.empty();
		
		cliente.setSenha(encryptPassword(cliente.getSenha()));
		
		return Optional.of(clienteRepository.save(cliente));
		
	}


public Optional<ClienteModel> atualizarUsuario(ClienteModel cliente) {

	
	if(clienteRepository.findById(cliente.getId()).isPresent()) {
		
		Optional<ClienteModel> searchUser = clienteRepository.findByUsuario(cliente.getUsuario());
		
		if ( (searchUser.isPresent()) && ( searchUser.get().getId() != cliente.getId()))
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Cliente já existe!", null);
		cliente.setSenha(encryptPassword(cliente.getSenha()));

		return Optional.ofNullable(clienteRepository.save(cliente));
		
	}
	
		return Optional.empty();

}


	public  String encryptPassword(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}


	private boolean comparePassword(String senhaDigita, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigita, senhaBanco);
				
	}

	private String generateBasicToken(String cliente, String password) {
		String token = cliente + ":" + password;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
	}


public Optional<ClienteLogin> autentificarUsuario(Optional<ClienteLogin> clienteLogin){
		
		Optional<ClienteModel> cliente = clienteRepository.findByUsuario(clienteLogin.get().getUsuario());
		
		if(cliente.isPresent()) {
			
			if(comparePassword(clienteLogin.get().getSenha(), cliente.get().getSenha())) {
				
				clienteLogin.get().setId(cliente.get().getId());
				clienteLogin.get().setNome(cliente.get().getNome());
				clienteLogin.get().setToken(generateBasicToken(clienteLogin.get().getUsuario(), clienteLogin.get().getSenha()));				
				clienteLogin.get().setSenha(cliente.get().getSenha());
				
				return clienteLogin;
			}
		}
		
		return Optional.empty();
	}




}
