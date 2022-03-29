package Elysian.SpringBootExercise.exceptions;

public class StoreNotFoundException extends RuntimeException{
    public StoreNotFoundException(Long id){
        super("Could not find store with id: "+id);
    }
}
