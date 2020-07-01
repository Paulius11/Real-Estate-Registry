package lt.task.realestateregistry.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class RecordController {

    @GetMapping
    public String getAllBuildingRecords(){
        return "List of buildings";
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
