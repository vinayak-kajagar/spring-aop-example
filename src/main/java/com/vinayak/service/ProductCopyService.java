package com.vinayak.service;

import com.vinayak.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductCopyService {


    public Product doSomething(Product product){
        return product;
    }
}
