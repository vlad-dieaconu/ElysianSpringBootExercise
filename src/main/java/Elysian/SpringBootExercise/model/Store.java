package Elysian.SpringBootExercise.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private String name;

    @OneToMany(mappedBy = "store")
    private List<Section> storeSections;

    public Store() {
    }

    public Store(String location, String name) {
        this.location = location;
        this.name = name;
    }

    public Store(String location, String name, List<Section> storeSections) {
        this.location = location;
        this.name = name;
        this.storeSections = storeSections;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Section> getStoreSections() {
        return storeSections;
    }

    public void setStoreSections(List<Section> storeSections) {
        this.storeSections = storeSections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
