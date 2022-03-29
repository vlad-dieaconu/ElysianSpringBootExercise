package Elysian.SpringBootExercise.controller;

import Elysian.SpringBootExercise.controller.request.StoreRequest;
import Elysian.SpringBootExercise.controller.response.MessageResponse;
import Elysian.SpringBootExercise.model.Store;
import Elysian.SpringBootExercise.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    StoreService storeService;


    @PostMapping("/create")
    public ResponseEntity<?> createStore(@RequestBody StoreRequest storeRequest){

        Store store = storeService.createStore(storeRequest);

        if(store != null)
            return ResponseEntity.ok("Store was created successfully!");
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Check your request body!"));

    }


    @GetMapping("/getAllStores")
    public ResponseEntity<?> getStores() {

        List<Store> stores = storeService.getStores();
        if (stores.size() > 0) {
            return ResponseEntity.ok(stores);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("No stores found"));
    }

    @GetMapping("/getStore")
    public ResponseEntity<?> getStore(@RequestParam("id") Long id){
        Optional<Store> store = storeService.getStore(id);
        if(store.isPresent()){
            return ResponseEntity.ok(store);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("\n" +
                "no store with id: "+ id +" was found"));
    }

    @PutMapping("/updateStore")
    public ResponseEntity<?> updateStore(@RequestParam("id") Long id, @RequestBody StoreRequest storeRequest ){
        Store store = storeService.updateStore(id,storeRequest);
        return ResponseEntity.ok(store);
    }





}
