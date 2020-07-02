package lt.task.realestateregistry.controller;


import lt.task.realestateregistry.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lt.task.realestateregistry.userservice.BuildingTaskService;

@RestController
@RequestMapping("/api/")
public class RecordController {

    @Autowired
    BuildingTaskService buildingTaskService;


    @GetMapping
    public Iterable<Building> getAllBuildingRecords(){
        return buildingTaskService.getAllBuildings();
    }

    @PostMapping
    public String createBuildingRecord(){
        return "creating building";
    }

    @PutMapping
    public String editBuildingRecord(){
        return "editing building";
    }

    @DeleteMapping
    public String deleteBuildingRecord(){
        return "deleting building";
    }
}
