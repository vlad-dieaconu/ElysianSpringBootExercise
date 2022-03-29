package Elysian.SpringBootExercise.service;

import Elysian.SpringBootExercise.controller.request.ProductRequest;
import Elysian.SpringBootExercise.exceptions.SectionNotFoundException;
import Elysian.SpringBootExercise.exceptions.StoreNotFoundException;
import Elysian.SpringBootExercise.model.Product;
import Elysian.SpringBootExercise.model.Section;
import Elysian.SpringBootExercise.model.Store;
import Elysian.SpringBootExercise.repositories.ProductRepository;
import Elysian.SpringBootExercise.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    SectionRepository sectionRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();
        if (productRequest.getName() != null) {
            product.setName(productRequest.getName());
            productRepository.save(product);
            return product;
        }
        return null;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Section addProductToSection(Long id, Long sectionId) {
        Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new SectionNotFoundException(sectionId));

        Product product = productRepository.findById(id).orElseThrow(() -> new SectionNotFoundException(id));

        product.setSection(section);

        productRepository.save(product);

        return section;
    }
}
