package lt.task.realestateregistry;

import lt.task.realestateregistry.doa.RecordRepository;
import lt.task.realestateregistry.model.BuildingModel;
import lt.task.realestateregistry.model.PropertyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RealEstateRegistryApplication {

	private static final Logger log = LoggerFactory.getLogger(RealEstateRegistryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RealEstateRegistryApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RecordRepository repo) {
		return (args) -> {

				repo.save(new BuildingModel("Vilnius", "Taikos g.", 25, "Paulius", 120, 900000, PropertyType.APARTMENT ));
				repo.save(new BuildingModel("Vilnius", "Basanaviciaus g.", 15, "Tomas", 520, 1000000, PropertyType.HOUSE ));
				repo.save(new BuildingModel("Kaunas", "Laisves g.", 15, "Jurgita", 320, 3500000, PropertyType.INDUSTRIAL ));


		};
	}


}