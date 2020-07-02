package lt.task.realestateregistry.userservice;

import lt.task.realestateregistry.doa.RecordRepository;
import lt.task.realestateregistry.exceptions.NotFoundException;
import lt.task.realestateregistry.model.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BuildingTaskService {

    private static final Logger log = LoggerFactory.getLogger(BuildingTaskService.class);


    @Autowired
    RecordRepository recordController;

    public Iterable<Building> getAllBuildings() {
        return recordController.findAll();
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
