package lt.task.realestateregistry.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lt.task.realestateregistry.doa.RecordRepository;
import lt.task.realestateregistry.model.BuildingModel;
import lt.task.realestateregistry.model.PropertyType;
import lt.task.realestateregistry.userservice.BuildingTaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RestControllerTest {

    @MockBean
    private BuildingTaskService service;

    @MockBean
    private RecordRepository repository;

    @Autowired
    private MockMvc mockMvc;

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("GET /widgets success")
    void testGetRecordsSuccess() throws Exception {
        // Setup our mocked service
        BuildingModel record1 = new BuildingModel("Vilnius", "Taikos g.", 25, "Paulius", 120, 900000, PropertyType.APARTMENT);
        BuildingModel record2 = new BuildingModel("Kaunas", "Basanaviciaus g.", 15, "Tomas", 520, 1000000, PropertyType.HOUSE);

        doReturn(Lists.newArrayList(record1, record2)).when(service).getAllBuildings();

        // Execute the GET request
        mockMvc.perform(get("/api/"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].city", is("Vilnius")))
                .andExpect(jsonPath("$[0].street", is("Taikos g.")))
                .andExpect(jsonPath("$[0].number", is(25)))
                .andExpect(jsonPath("$[0].owner", is("Paulius")))
                .andExpect(jsonPath("$[1].size", is(520)))
                .andExpect(jsonPath("$[1].marketValue", is(1000000.0)))
                .andExpect(jsonPath("$[1].propertyType", is("HOUSE")));
    }

    @Test
    @DisplayName("POST /api/")
    void testCreateRecord() throws Exception {
        // Setup our mocked service
        BuildingModel recordToPost = new BuildingModel("Vilnius", "Taikos g.", 25, "Paulius", 120, 900000, PropertyType.APARTMENT);
        BuildingModel recordToReturn = new BuildingModel(1L, "Vilnius", "Taikos g.", 25, "Paulius", 120, 900000, PropertyType.APARTMENT);

        doReturn(recordToPost).when(service).createBuilding(any());

        // Execute the POST request
        mockMvc.perform(post("/api/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recordToReturn)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.street", is("Taikos g.")))
                .andExpect(jsonPath("$.number", is(25)))
                .andExpect(jsonPath("$.owner", is("Paulius")))
                .andExpect(jsonPath("$.propertyType", is("APARTMENT")));
    }
}