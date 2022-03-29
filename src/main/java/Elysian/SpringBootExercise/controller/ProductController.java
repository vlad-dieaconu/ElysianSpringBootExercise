package Elysian.SpringBootExercise.controller;

import Elysian.SpringBootExercise.controller.request.ProductRequest;
import Elysian.SpringBootExercise.model.Product;
import Elysian.SpringBootExercise.model.Section;
import Elysian.SpringBootExercise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getProducts() {

        List<Product> productList = productService.getProducts();
        if (productList.size() > 0) {
            return ResponseEntity.ok(productList);
        }
        return ResponseEntity.ok("?");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        if (product != null)
            return ResponseEntity.ok("Product was created!");
        return ResponseEntity.ok("check req body");
    }

    @PutMapping("/addProductToSection")
    public ResponseEntity<?> addProductToSection(@RequestParam("id") Long id, @RequestParam("section_id") Long sectionId) {
        Section section = productService.addProductToSection(id,sectionId);
        return ResponseEntity.ok(section);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam("id") Long id) {
         productService.delete(id);
        return ResponseEntity.ok("deleted");

    }
}
