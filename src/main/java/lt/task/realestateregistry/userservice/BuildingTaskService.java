package lt.task.realestateregistry.userservice;

import lt.task.realestateregistry.doa.RecordRepository;
import lt.task.realestateregistry.exceptions.NotFoundException;
import lt.task.realestateregistry.model.BuildingModel;

import lt.task.realestateregistry.model.BuildingPostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingTaskService {

    private static final Logger log = LoggerFactory.getLogger(BuildingTaskService.class);


    @Autowired
    private RecordRepository recordController;

    @Autowired
    private BuildingModel buildingModel;


    private List<BuildingModel> listOfBuildings;

    public Iterable<BuildingModel> getAllBuildings() {
        listOfBuildings =  new ArrayList<>();
        log.info("Getting list of all records");
        return (List<BuildingModel>) recordController.findAll();
    }

    /**
     * Creates new task in project
     */
    public BuildingModel createBuilding(BuildingModel buildingModel) {
        log.info("Creating new building");
        BuildingModel save = recordController.save(buildingModel);
        log.debug(save.toString());
        return save;
    }



    public void deleteRecordById(Long id) {
        try {
            log.info("Deleting by id: " + id);
            recordController.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("id:" + id);
        }
    }
}
