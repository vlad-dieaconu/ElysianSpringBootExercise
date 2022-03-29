package Elysian.SpringBootExercise.exceptions;

public class SectionNotFoundException extends RuntimeException {
    public SectionNotFoundException(Long id){
        super("Could not find section with id: "+id);
    }
}
