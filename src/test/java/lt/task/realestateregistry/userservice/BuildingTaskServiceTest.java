package lt.task.realestateregistry.userservice;

import lt.task.realestateregistry.doa.RecordRepository;
import lt.task.realestateregistry.model.BuildingModel;
import lt.task.realestateregistry.model.PropertyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.StreamSupport;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class BuildingTaskServiceTest {

    @MockBean
    private RecordRepository repo;

    @Autowired
    private BuildingTaskService service;

    private BuildingModel record1 = new BuildingModel("Vilnius", "Taikos g.", 25, "Paulius", 120, 900000, PropertyType.APARTMENT );
    private BuildingModel record2 = new BuildingModel("Kaunas", "Basanaviciaus g.", 15, "Tomas", 520, 1000000, PropertyType.HOUSE );



    @Test
    void testEmpty() {
        when(repo.findAll()).thenReturn(Collections.emptyList());
        // Execute the service call
        Iterable<BuildingModel> allBuildings = service.getAllBuildings();

        // Assert the response
        long count = StreamSupport.stream(allBuildings.spliterator(), false).count();
        Assertions.assertEquals(0, count, "findAll should return 0 records");

    }

    @Test
    void getAllBuildings() {
        // Setup our mock repository
        doReturn(Arrays.asList(record1, record2)).when(repo).findAll();

        // Execute the service call
        Iterable<BuildingModel> allBuildings = service.getAllBuildings();

        // Assert the response
        long count = StreamSupport.stream(allBuildings.spliterator(), false).count();
        Assertions.assertEquals(2, count, "findAll should return 2 records");

    }

    @Test
    void createBuilding() {
        // Setup our mock repository
        doReturn(record1).when(repo).save(any());

        // Execute the service call
        BuildingModel building1 = service.createBuilding(record1);

        // Assert the response
        Assertions.assertNotNull(building1, "The saved record should not be null");
        Assertions.assertEquals("Vilnius", building1.getCity(), "The city should be needs to be correct");

    }

    @Test
    void deleteRecordById() {
//        // Setup our mock repository
//        doReturn(record1).when(repo).save(any());
//
//        // Execute the service call
//        BuildingModel building1 = service.createBuilding(record1);
//
//        Iterable<BuildingModel> allBuildings = service.getAllBuildings();
//        allBuildings.forEach(x-> System.out.println(x));
//        service.deleteBuildingById(3L);
//        // Assert the response
//        long count = StreamSupport.stream(allBuildings.spliterator(), false).count();
//        Assertions.assertEquals(0, count, "findAll should return 0 records");

    }

    @Test
    void editBuildingRecord() {
    }

}