package lt.task.realestateregistry.userservice;

import lt.task.realestateregistry.doa.RecordRepository;
import lt.task.realestateregistry.exceptions.NotFoundException;
import lt.task.realestateregistry.model.BuildingModel;

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
    private RecordRepository repo;

    @Autowired
    private BuildingModel buildingModel;


    private List<BuildingModel> listOfBuildings;

    public Iterable<BuildingModel> getAllBuildings() {
        listOfBuildings =  new ArrayList<>();
        log.info("Getting list of all records");
        return (List<BuildingModel>) repo.findAll();
    }

    /**
     * Creates new task in project
     */
    public BuildingModel createBuilding(BuildingModel buildingModel) {
        log.info("Creating new building");
        BuildingModel save = repo.save(buildingModel);
        log.debug(save.toString());
        return save;
    }



    public void deleteRecordById(Long id) {
        try {
            log.info("Deleting by id: " + id);
            repo.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("id:" + id);
        }
    }

    public void editBuildingRecord(BuildingModel newBuildingModel, Long id) {
        if (repo.findById(id).isPresent()) {
            BuildingModel oldRecord = repo.findById(id).get();
            oldRecord.setStreet(newBuildingModel.getStreet());
            oldRecord.setCity(newBuildingModel.getCity());
            oldRecord.setMarketValue(newBuildingModel.getMarketValue());
            oldRecord.setNumber(newBuildingModel.getNumber());
            oldRecord.setOwner(newBuildingModel.getOwner());
            oldRecord.setPropertyType(newBuildingModel.getPropertyType());
            oldRecord.setSize(newBuildingModel.getSize());
            repo.save(newBuildingModel);
        } else {
            throw new NotFoundException("Record not found:" + id);
        }
    }

    public void deleteBuildingById(long id) {
        repo.deleteById(id);
    }
}
