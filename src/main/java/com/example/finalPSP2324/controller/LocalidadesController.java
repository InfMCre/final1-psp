package com.example.finalPSP2324.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.corundumstudio.socketio.SocketIOServer;
import com.example.finalPSP2324.model.LocalidadGetResponse;
import com.example.finalPSP2324.model.LocalidadPostRequest;
import com.example.finalPSP2324.model.ProvinciaGetResponse;
import com.example.finalPSP2324.persistence.Localidad;
import com.example.finalPSP2324.persistence.Provincia;
import com.example.finalPSP2324.repository.LocalidadesRepository;
import com.example.finalPSP2324.repository.ProvinciasRepository;

@RestController
@RequestMapping("api")
public class LocalidadesController {


	@Autowired
	LocalidadesRepository localidadesRepository;
	

    private final SocketIOServer socketIoServer;
	

	@Autowired
	ProvinciasRepository provinciasRepository;
	
    @Autowired
    public LocalidadesController(SocketIOServer socketIoServer) {
        this.socketIoServer = socketIoServer;
        // this.fcm = fcm;
    }
	
	@GetMapping("/localidades")
	public ResponseEntity<List<LocalidadGetResponse>> getLocalidades() {
		
		List<LocalidadGetResponse> response = new ArrayList<LocalidadGetResponse>();
		
		Iterable<Localidad> repoResponse = localidadesRepository.findAll();
		
		
		for (Localidad localidad : repoResponse) {
			LocalidadGetResponse localidadResponse = new LocalidadGetResponse(
				localidad.getIdLocalidad(),
				localidad.getNombre(),
				localidad.getPoblacion(),
				localidad.getnProvincia()
			);
			response.add(localidadResponse);
		}
		
		return new ResponseEntity <List<LocalidadGetResponse>> (response, HttpStatus.OK);
	}
	
	
	@GetMapping("/localidades/{id}")
	public ResponseEntity<LocalidadGetResponse> getLocalidadById(@PathVariable("id") Integer id) {
		
		Localidad localidad = localidadesRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Localidad no encontrada")
		);
		
		LocalidadGetResponse response = new LocalidadGetResponse(
			localidad.getIdLocalidad(),
			localidad.getNombre(),
			localidad.getPoblacion(),
			localidad.getnProvincia()
		);
		
		Provincia provinciaLocalidad = localidad.getProvincia();
		ProvinciaGetResponse responseProvincia = new ProvinciaGetResponse(
				provinciaLocalidad.getnProvincia(),
				provinciaLocalidad.getNombre(),
				provinciaLocalidad.getSuperficie(),
				provinciaLocalidad.getIdCapital()
		);
		response.setProvincia(responseProvincia);
		
		return new ResponseEntity<LocalidadGetResponse> (response, HttpStatus.OK);
	}
	

	@PostMapping("/localidades")
	public ResponseEntity<LocalidadGetResponse> createLocalidad(@RequestBody LocalidadPostRequest localidadPostRequest) {
		Provincia provincia = provinciasRepository.findById(localidadPostRequest.getnProvincia()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Provincia no encontrada")
		);
		
		Localidad localidad = new Localidad(null, localidadPostRequest.getNombre(), localidadPostRequest.getPoblacion(), localidadPostRequest.getnProvincia());
		localidad.setProvincia(provincia);
		
		try {
			localidad = localidadesRepository.save(localidad);
			
			LocalidadGetResponse response = new LocalidadGetResponse(
				localidad.getIdLocalidad(),
				localidad.getNombre(),
				localidad.getPoblacion(),
				localidad.getnProvincia()
			);
			
			
			Provincia provinciaLocalidad = localidad.getProvincia();
			ProvinciaGetResponse responseProvincia = new ProvinciaGetResponse(
					provinciaLocalidad.getnProvincia(),
					provinciaLocalidad.getNombre(),
					provinciaLocalidad.getSuperficie(),
					provinciaLocalidad.getIdCapital()
			);
			response.setProvincia(responseProvincia);
			
			socketIoServer.getBroadcastOperations().sendEvent("LOCALIDAD_CREATED", response);
			
			return new ResponseEntity<LocalidadGetResponse> (response, HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<LocalidadGetResponse> (HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping("/localidades/{id}")
	public ResponseEntity<Void> deleteLocalidadById(@PathVariable("id") Integer id) {

		try {
			localidadesRepository.deleteById(id);
			socketIoServer.getBroadcastOperations().sendEvent("LOCALIDAD_DELETED", id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch  (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Localidad no encontrada");
		}
	}
}
