package Elysian.SpringBootExercise.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("Could not find PRODUCT with id: "+id);
    }
}
