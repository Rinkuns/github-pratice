package com.Ashokit.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Ashokit.binding.Product;
import com.Ashokit.repository.ProductRepository;

@RestController
public class ProductRestController {
	
	@Autowired
	private ProductRepository productRepo;
	@PostMapping(value = "/save",consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addProduct(Product product){
		productRepo.save(product);
		
		String msg="Product Saved Successfully";
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	@GetMapping(value = "/product/{pid}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductId(@PathVariable("pid") Integer productId){
		Product product=null;
		Optional<Product> findById = productRepo.findById(productId);
		
		if(findById.isPresent()) {
			 product=findById.get();
		}
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	@GetMapping(value = "/products",produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Product>> getAllProductS(){
		List<Product> findAll = productRepo.findAll();
		return new ResponseEntity<List<Product>>(findAll, HttpStatus.OK);
		
	}
	@PutMapping(value = "/update",consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> updateProduct(@RequestBody Product product){
		productRepo.save(product);
		return new ResponseEntity<String>("product updated",HttpStatus.OK);
	}
	@DeleteMapping(value ="/delete{pid}" )
	public ResponseEntity<String> deleteProduct(@PathVariable("pid") Integer productId){
		productRepo.deleteById(productId);
		return new ResponseEntity<String>("Product Deleted",HttpStatus.OK);
	}
}
