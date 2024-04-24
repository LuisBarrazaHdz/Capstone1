package backend.track;

import backend.track.models.Track;
import backend.track.service.TrackService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}

}

/*
@Component
class GoCmdLine implements CommandLineRunner
{
	//@Autowired
	final
	TrackService trackService;

	GoCmdLine(TrackService trackService) {
		this.trackService = trackService;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Run Spring Boot");

		Track trackAdd = Track.builder()
				.title("Prueba track")
				.album("Desconocido")
				.build();
		Track trackAdd2 = Track.builder()
				.title("Prueba track 2")
				.album("Desconocido")
				.build();
		trackService.addTrack(trackAdd);
		trackService.addTrack(trackAdd2);

		System.out.println(trackService.getTrackById(1));
		System.out.println(trackService.getTrackById(2));
	}
}
*/
