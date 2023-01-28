package com.javatechie.controller;

import com.javatechie.annotation.LogRequestAndResponse;
import com.javatechie.annotation.TrackExecutionTime;
import com.javatechie.entity.Product;
import com.javatechie.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService service;
//before
    @PostMapping
   // @LogRequestAndResponse
    @TrackExecutionTime
    public Product addProduct(@RequestBody Product product) {

        if(product.getPrice()<=100){
            throw new RuntimeException("Product price shouldn't be less than 100");
        }
        Product saveProduct = service.saveProduct(product);
        return saveProduct;
    }
    //after or after returning
    //After throwing advice
  //around advice : before + after returning
    @GetMapping
    @TrackExecutionTime
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) throws Exception {
        return service.getProductById(id);
    }

    @PutMapping("/{id}")
    //@LogRequestAndResponse
    public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest) {
        return service.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    @TrackExecutionTime
    public long deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }

}
