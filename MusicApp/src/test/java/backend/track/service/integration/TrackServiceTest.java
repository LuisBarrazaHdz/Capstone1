package backend.track.service.integration;

import backend.track.models.Artist;
import backend.track.models.MediaType;
import backend.track.models.Track;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TrackServiceTest {
    @Autowired
    @Qualifier("trackService")
    private TrackService trackService;

    @Autowired
    private ApplicationContext context;

    private List<Track> inicialize() {
        List<Track> tracks = new ArrayList<>();
        List<Artist> artistList = new ArrayList<>();
        artistList.add(new Artist(1, "Taylor Swift"));
        artistList.add(new Artist(2, "Amanda Miguel"));
        tracks.add(Track.builder()
                .title("Karma is a cat")
                .album("Lover")
                .artists(artistList)
                .issueDate(LocalDate.of(2024, 4, 20))
                .mediaType(MediaType.Mp3)
                .duration(LocalTime.of(0,2,30))
                .build());
        tracks.add(Track.builder()
                .title("La ruleta")
                .album("Exitos de oro")
                .artists(artistList)
                .issueDate(LocalDate.of(2024, 4, 23))
                .mediaType(MediaType.Mp3)
                .duration(LocalTime.of(0,4,00))
                .build());
        return tracks;
    }

    @Test
    public void addTrackTest(){
        List<Artist> artistList = new ArrayList<>();
        artistList.add(new Artist(1, "Taylor Swift"));
        Track trackAdd = (Track.builder()
                .title("Karma is a cat")
                .album("Lover")
                .artists(artistList)
                .issueDate(LocalDate.of(2024, 4, 20))
                .mediaType(MediaType.Mp3)
                .duration(LocalTime.of(0,2,30))
                .build());

        Track trackNew = trackService.addTrack(trackAdd);

        Track result = trackService.getTrackById(trackNew.getIdTrack());

        assertNotNull(result);
        assertEquals(result.getTitle(), trackAdd.getTitle());

    }

    @Test
    public void deleteTrack() {
        List<Track> tracks = inicialize();
        Track trackNew = trackService.addTrack(tracks.get(1));

        boolean result = trackService.deleteTrack(1);

        assertTrue(result);
        assertNull(trackService.getTrackById(1));
    }

    @Test
    public void updateTrack(){
        List<Track> tracks = inicialize();
        Track trackNew = trackService.addTrack(tracks.get(1));
        trackNew.setTitle("Update Title");

        boolean result = trackService.updateTrack(trackNew);

        assertTrue(result);
        assertEquals("Update Title", trackService.getTrackById(1).getTitle());
    }
}
