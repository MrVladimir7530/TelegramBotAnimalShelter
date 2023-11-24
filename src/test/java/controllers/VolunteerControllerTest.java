package controllers;

import com.example.telegrambotanimalshelter.controllers.VolunteerController;
import com.example.telegrambotanimalshelter.model_services.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(VolunteerController.class)
public class VolunteerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private VolunteerController volunteerController;
    @SpyBean
    private VolunteerService volunteerService;


    public void test() {

    }
    //todo дописать

}