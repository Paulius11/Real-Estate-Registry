package lt.task.realestateregistry.doa;

import lt.task.realestateregistry.model.BuildingModel;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<BuildingModel, Long> {
}
