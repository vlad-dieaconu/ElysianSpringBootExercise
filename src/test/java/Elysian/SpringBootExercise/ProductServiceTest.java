package Elysian.SpringBootExercise;

import Elysian.SpringBootExercise.model.Product;
import Elysian.SpringBootExercise.model.Section;
import Elysian.SpringBootExercise.repositories.ProductRepository;
import Elysian.SpringBootExercise.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    @DisplayName("Given there are available products, when retrieving the products then products are retrieved correctly")
    void givenThereAreAvailableProducts_whenRetrievingProducts_thenProductsAreRetrievedCorrectly(){

        Section section1 = new Section("Food");
        Section section2 = new Section("Electronics");

        final List<Product> products = Arrays.asList(
                new Product("Paine",section1),
                new Product("Laptop ASUS", section2)
        );

        when(productRepository.findAll()).thenReturn(products);

        final List<Product> allProducts = productService.getProducts();

        assertNotNull(allProducts, "The products are null");
        assertEquals(allProducts.size(), products.size(), "Not all the products were returned");
        assertTrue(allProducts.iterator().hasNext(), "There is just a single product");

        allProducts.forEach(productDTO -> {
            assertThat(productDTO.getId(), not(0));
            assertThat("The name must not be null or empty", StringUtils.hasLength(productDTO.getName()));
        });


    }


}
