package lt.task.realestateregistry.controller;


import io.swagger.annotations.ApiOperation;
import lt.task.realestateregistry.model.BuildingModel;
import lt.task.realestateregistry.model.BuildingPostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lt.task.realestateregistry.userservice.BuildingTaskService;

@RestController
@RequestMapping("/api/")
public class RecordController {

    @Autowired
    BuildingTaskService buildingTaskService;


    @GetMapping
    @ApiOperation(value = "Get building records", notes = "Returns list of building records.")
    public Iterable<BuildingModel> getAllBuildingRecords(){
        return buildingTaskService.getAllBuildings();
    }

    @PostMapping
    @ApiOperation(value = "Create building record", notes = "Creates building record.")
    public BuildingModel createBuildingRecord(@RequestBody BuildingModel buildingModel){
        return buildingTaskService.createBuilding(buildingModel);
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
