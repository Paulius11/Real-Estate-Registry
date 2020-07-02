package lt.task.realestateregistry.userservice;

import lt.task.realestateregistry.doa.RecordRepository;
import lt.task.realestateregistry.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingTaskService {



    @Autowired
    RecordRepository recordController;

    public Iterable<Building> getAllBuildings() {
        return recordController.findAll();
    }

    public void deleteRecordById(Long id) {
        try {

            repositoryProject.deleteById(id);
        } catch (Exception e) {
            throw new UserNotFoundException("id:" + id);
        }
    }
    }
}
