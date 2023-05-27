package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.repositories.ProductRepo;


@Service
public class ProductService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ProductRepo productRepository;
	
	public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    
    public Product createProduct(Product product , Long id) {
    	Category c = categoryRepo.findById(id).orElse(null);
    	if(c != null) {
    		product.setCategory(c);
    		return productRepository.save(product);
    	}
        return productRepository.save(product);
    }

    
    public Product updateProduct(long id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        // Update other fields as needed
        return productRepository.save(existingProduct);
    }

    
    public void deleteProduct(long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

}
