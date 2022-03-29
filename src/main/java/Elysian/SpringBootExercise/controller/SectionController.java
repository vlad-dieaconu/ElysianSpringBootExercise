package Elysian.SpringBootExercise.controller;

import Elysian.SpringBootExercise.controller.request.SectionRequest;
import Elysian.SpringBootExercise.controller.response.MessageResponse;
import Elysian.SpringBootExercise.model.Section;
import Elysian.SpringBootExercise.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/section")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @GetMapping("/getSections")
    public ResponseEntity<?> getAllSections(){

        List<Section> sections = sectionService.getSections();

        if(sections.size() > 0){
            return ResponseEntity.ok(sections);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("No sections found"));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSection(@RequestBody SectionRequest sectionRequest){
        Section section = sectionService.createSection(sectionRequest);
        if(section != null)
            return ResponseEntity.ok("Section was created successfully!");
        else
            return ResponseEntity.badRequest().body(new MessageResponse("Check your request body!"));
    }

    @PutMapping("/addSectionToStore")
    public ResponseEntity<?> addSectionToStore(@RequestParam("id") Long id, @RequestParam("store_id") Long storeId){
        Section section = sectionService.addSectionToStore(id,storeId);
        return ResponseEntity.ok("Section was added to the store");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        String result =  sectionService.deleteSection(id);
        return ResponseEntity.ok(result);
    }


}
