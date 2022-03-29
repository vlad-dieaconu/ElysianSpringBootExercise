package Elysian.SpringBootExercise;



import Elysian.SpringBootExercise.controller.request.StoreRequest;
import Elysian.SpringBootExercise.exceptions.StoreNotFoundException;
import Elysian.SpringBootExercise.model.Store;
import Elysian.SpringBootExercise.repositories.StoreRepository;
import Elysian.SpringBootExercise.service.StoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;




@ExtendWith(SpringExtension.class)
public class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    @Test
    @DisplayName("Given there are available stores, when retrieving a store by id, then the store is returned")
    void givenThereAreAvailableStores_whenRetrievingAStoreById_thenReturnTheCorrectStore(){

        final int id = 1;
        final Store store = mock(Store.class);
        final String storeName = "Auchan";

        when(store.getName()).thenReturn(storeName);
        when(store.getId()).thenReturn((long) id);

        when(storeRepository.findById((long)id)).thenReturn(Optional.of(store));

        final Optional<Store> resulted = storeService.getStore((long) id);
        assertNotNull(resulted);
        assertThat(resulted.get().getName(),is(storeName));
        assertThat(resulted.get().getId(), is((long)id));
    }

    @Test
    @DisplayName("Given there are no store, when retrieving a store by ID then an Store not found exception is thrown")
    void givenThereAreNoAvailableStore_whenRetrievingAStoreById_thenAnStoreNotFoundExceptionIsThrown() {
        StoreRequest storeRequest = new StoreRequest();
        assertThrows(StoreNotFoundException.class, () -> storeService.updateStore((long)55, storeRequest));
    }

}
