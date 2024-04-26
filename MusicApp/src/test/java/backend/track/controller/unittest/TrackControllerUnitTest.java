package backend.track.controller.unittest;

import backend.track.controller.TrackController;
import backend.track.models.Artist;
import backend.track.models.MediaType;
import backend.track.models.Track;
import backend.track.service.TrackService;
import backend.track.utils.UriCreator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class TrackControllerUnitTest {
    @InjectMocks
    private TrackController trackController;

    @Mock
    private TrackService trackService;

    @Mock
    private UriCreator uriCreator;

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
    public void addTrackTest() throws Exception {
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

        URI newTrackURI = new URI("localhost:8080/track/getArtistByTrack/1");

        Mockito.when(trackService.addTrack(trackAdd)).thenReturn(trackAdd);
        Mockito.when(uriCreator.getUriFor(1)).thenReturn(newTrackURI);

        ResponseEntity<?> response = trackController.addAdopter(trackAdd);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(newTrackURI, response.getHeaders().getLocation());



        Mockito.verify(trackService).addTrack(trackAdd);
        Mockito.verify(uriCreator).getUriFor(1);

    }
}
