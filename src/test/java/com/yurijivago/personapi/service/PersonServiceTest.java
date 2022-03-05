package com.yurijivago.personapi.service;

import com.yurijivago.personapi.dto.MessageResponseDTO;
import com.yurijivago.personapi.dto.request.PersonDTO;
import com.yurijivago.personapi.entity.Person;
import com.yurijivago.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.yurijivago.personapi.utils.PersonUtils.createFakeDTO;
import static com.yurijivago.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage(){
        PersonDTO personDTO = createFakeDTO();
        Person exceptedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(exceptedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(exceptedSavedPerson.getId());

        MessageResponseDTO successMessage = personService.createPerson(personDTO);

        assertEquals(expectedSuccessMessage, successMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }

}
