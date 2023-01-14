package com.trustbridge.restaurant.controller;

import java.util.List;

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

import com.trustbridge.restaurant.bean.Product;
import com.trustbridge.restaurant.dto.TransactionRequest;
import com.trustbridge.restaurant.service.RestaurantService;

@RestController
@RequestMapping("/dann-resto")
public class RestaurantController {

	@Autowired
	RestaurantService restoService;

	@GetMapping(value = "/get-products")
	public List<Product> getEmployees() {
		return restoService.getProducts();
	}
	
	@GetMapping(value = "/view")
	public ResponseEntity orderProducts(@RequestParam String referenceId) throws Exception {
		try {
			return new ResponseEntity(restoService.viewOrder(referenceId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/order")
	public ResponseEntity sendOrder(@RequestBody TransactionRequest request) throws Exception {
		try {
			return new ResponseEntity(restoService.sendOrder(request), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/change")
	public ResponseEntity change(@RequestBody TransactionRequest request) throws Exception {
		try {
			return new ResponseEntity(restoService.updateOrder(request), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/cancel")
	public ResponseEntity cancel(@RequestParam String referenceId) throws Exception {
		try {
			return new ResponseEntity(restoService.cancelOrder(referenceId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
