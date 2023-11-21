package model_services;

import com.example.telegrambotanimalshelter.model_services.AdopterServiceImpl;
import com.example.telegrambotanimalshelter.repositories.AdopterRepository;
import com.example.telegrambotanimalshelter.services.TelegramBot;
import constants.AdopterConstant;
import constants.AdopterServiceConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static constants.AdopterConstant.*;
import static constants.AdopterServiceConstant.*;
import static org.mockito.Mockito.*;




public class AdopterServiceImplTest {
    private TelegramBot telegramBotMock;
    private AdopterRepository adopterRepositoryMock;
    private AdopterServiceImpl out;

    @BeforeEach
    public void init() {
        telegramBotMock = mock(TelegramBot.class);
        adopterRepositoryMock = mock(AdopterRepository.class);
        out = new AdopterServiceImpl(telegramBotMock, adopterRepositoryMock);
    }

    @Test
    public void shouldCorrectResultFromMethodFindAdoptersOfShelterAnimals() {
        when(adopterRepositoryMock.findAdoptersOfShelterAnimals(1L)).thenReturn(ADOPTERS_OF_SHELTER_1);
        when(adopterRepositoryMock.findAdoptersOfShelterAnimals(2L)).thenReturn(ADOPTERS_OF_SHELTER_2);
        Assertions.assertEquals(ADOPTERS_OF_SHELTER_1, out.findAdoptersOfShelterAnimals(1L));
        Assertions.assertEquals(ADOPTERS_OF_SHELTER_2, out.findAdoptersOfShelterAnimals(2L));

        verify(adopterRepositoryMock, times(1)).findAdoptersOfShelterAnimals(1L);
        verify(adopterRepositoryMock, times(1)).findAdoptersOfShelterAnimals(2L);
    }
    @Test
    public void shouldCorrectResultFromMethodFindById() {
        when(adopterRepositoryMock.findById(1L)).thenReturn(Optional.of(ADOPTER1));
        when(adopterRepositoryMock.findById(3L)).thenReturn(Optional.of(ADOPTER3));

        Assertions.assertEquals(ADOPTER1, out.findById(1L));
        Assertions.assertEquals(ADOPTER3, out.findById(3L));

        verify(adopterRepositoryMock, times(1)).findById(1L);
        verify(adopterRepositoryMock, times(1)).findById(3L);
    }
    @Test
    public void shouldCorrectResultFromMethodBySubscriberId() {
        when(adopterRepositoryMock.findBySubscriberChatId(1L)).thenReturn(ADOPTER1);
        when(adopterRepositoryMock.findBySubscriberChatId(3L)).thenReturn(ADOPTER3);

        Assertions.assertEquals(ADOPTER1, out.findBySubscriberId(1L));
        Assertions.assertEquals(ADOPTER3, out.findBySubscriberId(3L));

        verify(adopterRepositoryMock, times(1)).findBySubscriberChatId(1L);
        verify(adopterRepositoryMock, times(1)).findBySubscriberChatId(3L);
    }
    @Test
    public void shouldCorrectResultFromMethodGetActualAdopter() {
        when(adopterRepositoryMock.getActualAdopter()).thenReturn(ACTUAL_ADOPTERS);

        Assertions.assertEquals(ACTUAL_ADOPTERS, out.getActualAdopter());

        verify(adopterRepositoryMock, times(1)).getActualAdopter();
    }
    @Test
    public void shouldCorrectResultFromMethodCreate() {
        when(adopterRepositoryMock.save(ADOPTER1)).thenReturn(ADOPTER1);
        when(adopterRepositoryMock.save(ADOPTER2)).thenReturn(ADOPTER2);
        when(adopterRepositoryMock.save(ADOPTER3)).thenReturn(ADOPTER3);

        Assertions.assertEquals(ADOPTER1, out.create(ADOPTER1));
        Assertions.assertEquals(ADOPTER2, out.create(ADOPTER2));
        Assertions.assertEquals(ADOPTER3, out.create(ADOPTER3));

        verify(adopterRepositoryMock, times(1)).save(ADOPTER1);
        verify(adopterRepositoryMock, times(1)).save(ADOPTER2);
        verify(adopterRepositoryMock, times(1)).save(ADOPTER3);
    }
    @Test
    public void shouldThrowNoSuchElementExceptionWhenIdNoExist() {
        when(adopterRepositoryMock.findById(10L)).thenThrow(NoSuchElementException.class);

        Assertions.assertThrows(NoSuchElementException.class, () -> out.editTrialPeriod(10L, 15, null));
        Assertions.assertThrows(NoSuchElementException.class, () -> out.editTrialPeriod(10L, null, true));

        verify(adopterRepositoryMock, times(2)).findById(10L);

    }
    @Test
    public void shouldCorrectResultFromMethodEditTrialPeriodWhenDaysIs_15() {
        when(adopterRepositoryMock.findById(1L)).thenReturn(Optional.of(ADOPTER1));
        when(adopterRepositoryMock.findById(2L)).thenReturn(Optional.of(ADOPTER2));

        when(adopterRepositoryMock.save(ADOPTER1)).thenReturn(ADOPTER1);
        when(adopterRepositoryMock.save(ADOPTER2)).thenReturn(ADOPTER2);

        Assertions.assertEquals(ADOPTER1, out.editTrialPeriod(1L, 15, null));
        Assertions.assertEquals(ADOPTER2, out.editTrialPeriod(2L, 15, null));


        verify(adopterRepositoryMock, times(1)).findById(1L);
        verify(adopterRepositoryMock, times(1)).findById(2L);
        verify(adopterRepositoryMock, times(1)).save(ADOPTER1);
        verify(adopterRepositoryMock, times(1)).save(ADOPTER2);

    }



}
