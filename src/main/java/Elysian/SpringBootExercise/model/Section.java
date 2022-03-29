package Elysian.SpringBootExercise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "section")
    private List<Product> products;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;



    public Section(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Section(String name) {
        this.name = name;
    }

    public Section() {

    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
