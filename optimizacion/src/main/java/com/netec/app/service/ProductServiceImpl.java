package com.netec.app.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netec.app.entities.Product;
import com.netec.app.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

 
    private final ProductRepository productRepository;
	
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
	@Override
	public List<Product> findAll() {
		return (List<Product>)productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Product save(Product client) {
		 return productRepository.save(client);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
   
}
