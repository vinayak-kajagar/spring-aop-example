package com.javatechie.service;

import com.javatechie.annotation.LogRequestAndResponse;
import com.javatechie.annotation.TrackExecutionTime;
import com.javatechie.entity.Product;
import com.javatechie.respository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    //@LogRequestAndResponse
    @TrackExecutionTime
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @TrackExecutionTime
    public List<Product> getProducts() {
           return repository.findAll();
    }

    //@LogRequestAndResponse
    public Product getProductById(int id) {
           return repository.findById(id)
                   .orElseThrow(()->new IllegalArgumentException("product is not available with id "+id));
    }

  @TrackExecutionTime
    public Product updateProduct(int id, Product productRequest) {
        // get the product from DB by id
        // update with new value getting from request
        Product existingProduct = repository.findById(id).get(); // DB
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(existingProduct.getProductType());
        return repository.save(existingProduct);
    }

    public long deleteProduct(int id) {
        repository.deleteById(id);
        return repository.count();
    }

}
