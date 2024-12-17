package com.atividades.controller;

import java.util.List;
import java.util.Optional;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atividades.DTOs.BluerayDTO;
import com.atividades.models.Blueray;
import com.atividades.repository.BluerayRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BluerayController {
	@Autowired
	private BluerayRepository bluerayRepository;

	@RequestMapping(value = "/blueray", method = RequestMethod.GET)
	public List<Blueray> Get() {
		return bluerayRepository.findAll();
	}

	@RequestMapping(value = "blueray/{id}", method = RequestMethod.GET)
	public ResponseEntity<Blueray> GetById(@PathVariable(value = "id") long id) {
		Optional<Blueray> blueray = bluerayRepository.findById(id);
		if (blueray.isPresent()) {
			return new ResponseEntity<Blueray>(blueray.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/blueray/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Blueray>> GetByName(@PathVariable(value = "name") String name) {
		List<Blueray> bluerays = bluerayRepository.findByNomeContainingIgnoreCase(name);
		if (!bluerays.isEmpty()) {
			return new ResponseEntity<>(bluerays, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/blueray", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> Post(@RequestPart("image") MultipartFile imageFile, @RequestPart("data") String data)
			throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		BluerayDTO registerData = objectMapper.readValue(data, BluerayDTO.class);

		// Salva a imagem e retorna o caminho relativo para a URL
		String imagePath = saveImage(imageFile);

		// Cria o registro com o caminho acessível pela URL pública
		Blueray newBlueray = new Blueray(registerData.nome(), registerData.descricao(), registerData.preco(), imagePath,
				registerData.season());

		this.bluerayRepository.save(newBlueray);

		return ResponseEntity.ok(newBlueray);
	}

	@Value("${app.upload.dir}")
	private String uploadsDir;

	private String saveImage(MultipartFile imageFile) throws IOException {
		File uploadDir = new File(uploadsDir);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
		String filePath = uploadsDir + File.separator + fileName; // Caminho físico

		// Salva o arquivo no sistema de arquivos
		File destinationFile = new File(filePath);
		imageFile.transferTo(destinationFile);

		// Retorna apenas o caminho relativo usado para URL pública
		return "http://localhost:8080/uploads/" + fileName; // Caminho acessível via servidor
	}

	/*
	 * @RequestMapping(value = "/blueray/{id}", method = RequestMethod.PUT) public
	 * ResponseEntity<Blueray> Put(@PathVariable(value = "id") long id, @RequestBody
	 * Blueray newBlueray) { Optional<Blueray> oldBlueray =
	 * bluerayRepository.findById(id); if (oldBlueray.isPresent()) { Blueray blueray
	 * = oldBlueray.get(); blueray.setNome(newBlueray.getNome());
	 * blueray.setDescricao(newBlueray.getDescricao());
	 * blueray.setPreco(newBlueray.getPreco());
	 * blueray.setImagem(newBlueray.getImagem());
	 * blueray.setSeason(newBlueray.getSeason());
	 * 
	 * bluerayRepository.save(blueray); return new ResponseEntity<Blueray>(blueray,
	 * HttpStatus.OK); } else { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * }
	 */

	@RequestMapping(value = "/blueray/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Blueray> Put(@RequestPart(value = "image", required = false) MultipartFile imageFile, @RequestPart("data") String data)
			throws IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		BluerayDTO registerData = objectMapper.readValue(data, BluerayDTO.class);
		Optional<Blueray> oldBlueraySearch = bluerayRepository.findById(registerData.id());
		if (oldBlueraySearch.isPresent()) {
			String imagePath;
			Blueray oldBlueray = oldBlueraySearch.get();
			if(imageFile == null) {
				imagePath = oldBlueray.getImagem();
			}else {
				File fileToDelete = new File(oldBlueray.getImagem());
				if (fileToDelete.exists()) {
					fileToDelete.delete();
				}
				imagePath = saveImage(imageFile);				
			}
			oldBlueray.setNome(registerData.nome());
			oldBlueray.setDescricao(registerData.descricao());
			oldBlueray.setPreco(registerData.preco());
			oldBlueray.setImagem(imagePath);
			oldBlueray.setSeason(registerData.season());
			bluerayRepository.save(oldBlueray);
			
			return ResponseEntity.ok(oldBlueray);
			//return new ResponseEntity<Blueray>(oldBlueray, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/blueray/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<Blueray> blueray = bluerayRepository.findById(id);
		if (blueray.isPresent()) {
			bluerayRepository.delete(blueray.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
