package Elysian.SpringBootExercise.service;

import Elysian.SpringBootExercise.controller.request.SectionRequest;
import Elysian.SpringBootExercise.exceptions.SectionNotFoundException;
import Elysian.SpringBootExercise.exceptions.StoreNotFoundException;
import Elysian.SpringBootExercise.model.Section;
import Elysian.SpringBootExercise.model.Store;
import Elysian.SpringBootExercise.repositories.SectionRepository;
import Elysian.SpringBootExercise.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    StoreRepository storeRepository;


    public List<Section> getSections(){
        return sectionRepository.findAll();
    }

    public Section createSection(SectionRequest sectionRequest){
        if(sectionRequest.getName() != null) {
            sectionRepository.save(new Section(sectionRequest.getName()));
            return new Section(sectionRequest.getName());
        }
        return null;
    }

    public Section addSectionToStore(Long id, Long storeId) {
        Section section = sectionRepository.findById(id).orElseThrow(() -> new SectionNotFoundException(id));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreNotFoundException(storeId));

        section.setStore(store);

        sectionRepository.save(section);

        return section;
    }

    public String deleteSection(Long id) {
        Section section = sectionRepository.findById(id).orElseThrow(() -> new SectionNotFoundException(id));
        sectionRepository.delete(section);
        return "Section was deleted!";
    }
}
