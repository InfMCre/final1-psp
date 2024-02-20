package com.example.finalPSP2324.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.finalPSP2324.model.LocalidadGetResponse;
import com.example.finalPSP2324.model.ProvinciaGetResponse;
import com.example.finalPSP2324.persistence.Localidad;
import com.example.finalPSP2324.persistence.Provincia;
import com.example.finalPSP2324.repository.ProvinciasRepository;

@RestController
@RequestMapping("api")
public class ProvinciasController {


	@Autowired
	ProvinciasRepository provinciasRepository;
	
	@GetMapping("/provincias")
	public ResponseEntity<List<ProvinciaGetResponse>> getProvincias() {
		
		List<ProvinciaGetResponse> response = new ArrayList<ProvinciaGetResponse>();
		
		Iterable<Provincia> repoResponse = provinciasRepository.findAll();
		
		
		for (Provincia provincia : repoResponse) {
			ProvinciaGetResponse localidadResponse = new ProvinciaGetResponse(
					provincia.getnProvincia(),
					provincia.getNombre(),
					provincia.getSuperficie(),
					provincia.getIdCapital()
			);
			response.add(localidadResponse);
		}
		
		return new ResponseEntity <List<ProvinciaGetResponse>> (response, HttpStatus.OK);
	}

	
	@GetMapping("/provincias/{id}")
	public ResponseEntity<ProvinciaGetResponse> getProvinciaById(@PathVariable("id") Integer id) {
		
		Provincia provincia = provinciasRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Provincia no encontrada")
		);
		
		ProvinciaGetResponse response = new ProvinciaGetResponse(
				provincia.getnProvincia(),
				provincia.getNombre(),
				provincia.getSuperficie(),
				provincia.getIdCapital()
		);
		
		return new ResponseEntity<ProvinciaGetResponse> (response, HttpStatus.OK);
	}
	

	
	@GetMapping("/provincias/{id}/localidades")
	public ResponseEntity<List<LocalidadGetResponse>> getLocalidadesByProvinciaId(@PathVariable("id") Integer provinciaId) {

		Provincia provincia = provinciasRepository.findById(provinciaId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Provincia no encontrada")
		);
		
		List<LocalidadGetResponse> response = new ArrayList<LocalidadGetResponse>();
		
		Iterable<Localidad> localidadesProvincia = provincia.getLocalidades();

		for (Localidad localidad : localidadesProvincia) {
			LocalidadGetResponse localidadResponse = new LocalidadGetResponse(
				localidad.getIdLocalidad(),
				localidad.getNombre(),
				localidad.getPoblacion(),
				localidad.getnProvincia()
			);
			response.add(localidadResponse);
		}
		
		return new ResponseEntity<List<LocalidadGetResponse>> (response, HttpStatus.OK);
	}
	
	
	
}
	