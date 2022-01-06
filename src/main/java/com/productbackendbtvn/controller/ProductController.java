package com.productbackendbtvn.controller;

import com.productbackendbtvn.dto.response.ResponseMessage;
import com.productbackendbtvn.model.Product;
import com.productbackendbtvn.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = (List<Product>) productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        if(productService.existsByName(product.getName())){
            return new ResponseEntity<>(new ResponseMessage("no_name_product"), HttpStatus.OK);
        }
        if(product.getAvatar()==null){
            return new ResponseEntity<>(new ResponseMessage("no_avatar_product"), HttpStatus.OK);
        }
        productService.save(product);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Optional<Product> productOptional = productService.findById(id);
        if(!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.save(productOptional.get());
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailProduct(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if(!product.isPresent()){
            return new ResponseEntity<>(new ResponseMessage("khong co"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if(!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
}
