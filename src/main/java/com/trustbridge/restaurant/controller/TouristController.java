package com.trustbridge.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trustbridge.restaurant.api.Tourist;
import com.trustbridge.restaurant.api.TouristRecord;
import com.trustbridge.restaurant.service.TouristService;

@RestController
@RequestMapping("/dann-tourist")
public class TouristController {

	@Autowired
	TouristService touristService;

	@GetMapping(value = "/get-tourist")
	public ResponseEntity getTrourist() {
		try {
			return new ResponseEntity(touristService.getTourists(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/get-tourist-by-id")
	public ResponseEntity getTrourist(@RequestParam String id) {
		try {
			return new ResponseEntity(touristService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/create")
	public ResponseEntity create(@RequestBody Tourist tourist) throws Exception {
		try {
			return new ResponseEntity(touristService.create(tourist), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity update(@RequestBody Tourist tourist) throws Exception {
		try {
			return new ResponseEntity(touristService.update(tourist), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity delete(@RequestParam String referenceId) throws Exception {
		try {
			return new ResponseEntity(touristService.delete(referenceId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
