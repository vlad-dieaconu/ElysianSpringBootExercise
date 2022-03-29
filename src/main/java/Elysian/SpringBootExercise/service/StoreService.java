package Elysian.SpringBootExercise.service;

import Elysian.SpringBootExercise.controller.request.StoreRequest;
import Elysian.SpringBootExercise.exceptions.SectionNotFoundException;
import Elysian.SpringBootExercise.exceptions.StoreNotFoundException;
import Elysian.SpringBootExercise.model.Section;
import Elysian.SpringBootExercise.model.Store;
import Elysian.SpringBootExercise.repositories.SectionRepository;
import Elysian.SpringBootExercise.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    SectionRepository sectionRepository;


    public Store createStore(StoreRequest storeRequest){
        if(storeRequest.getLocation() != null && storeRequest.getName() != null) {
            storeRepository.save(new Store(storeRequest.getLocation(), storeRequest.getName()));
            return new Store(storeRequest.getLocation(), storeRequest.getName());
        }
        return null;
    }

    public List<Store> getStores(){
        return storeRepository.findAll();
    }

    public Optional<Store> getStore(Long id){
        return storeRepository.findById(id);
    }

    public Store updateStore(Long storeId, StoreRequest storeRequest){

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreNotFoundException(storeId));

        store.setLocation(storeRequest.getLocation());
        store.setName(storeRequest.getName());

        storeRepository.save(store);

        return store;
    }

}
