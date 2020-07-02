package lt.task.realestateregistry.doa;

import lt.task.realestateregistry.model.Building;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Building, Long> {
}
