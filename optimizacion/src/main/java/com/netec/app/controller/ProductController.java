package com.netec.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.netec.app.entities.Product;
import com.netec.app.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.findById(id)
                .map(product -> ResponseEntity.ok(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createClient(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.findById(id)
                .map(product -> {
                	product.setNombre(productDetails.getNombre());
                	product.setDescripcion(productDetails.getDescripcion());
                	product.setPrecio(productDetails.getPrecio());
                	Product updatedProduct = productService.save(product);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.findById(id).isPresent()) {
        	productService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
