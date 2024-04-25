package backend.track.service.integration;

import backend.track.models.Artist;
import backend.track.models.MediaType;
import backend.track.models.Track;
import backend.track.service.ArtistService;
import backend.track.service.TrackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ArtistServiceTest {
    @Autowired
    @Qualifier("artistService")
    private ArtistService artistService;

    @Autowired
    private ApplicationContext context;

    private List<Artist> inicialize() {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(new Artist(1, "Taylor Swift"));
        artistList.add(new Artist(2, "Luis Miguel"));
        artistList.add(new Artist(3, "Vicente Fernandez"));
        artistList.add(new Artist(4, "Amanda Miguel"));
        return artistList;
    }

    @Test
    public void addTrackTest(){
        List<Artist> artistsAdd = inicialize();

        Artist artistNew = artistService.addArtist(artistsAdd.get(1));

        Artist result = artistService.getArtistById(artistNew.getIdArtist());

        assertNotNull(result);
        assertEquals(result.getName(), artistsAdd.get(1).getName());
    }

    @Test
    public void deleteTrack() {
        List<Artist> artistsAdd = inicialize();
        Artist artistNew = artistService.addArtist(artistsAdd.get(1));

        boolean result = artistService.deleteArtist(1);

        assertTrue(result);
        assertNull(artistService.getArtistById(1));
    }

    @Test
    public void updateTrack(){
        List<Artist> artistsAdd = inicialize();
        Artist artistNew = artistService.addArtist(artistsAdd.get(1));
        artistNew.setName("Update Artist");

        boolean result = artistService.updateArtist(artistNew);

        assertTrue(result);
        assertEquals("Update Artist", artistService.getArtistById(1).getName());
    }
}
