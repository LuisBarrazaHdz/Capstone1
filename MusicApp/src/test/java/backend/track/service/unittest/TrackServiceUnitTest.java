package backend.track.service.unittest;

import backend.track.dao.TrackDAO;
import backend.track.models.Artist;
import backend.track.models.MediaType;
import backend.track.models.Track;
import backend.track.price.PriceProvider;
import backend.track.service.TrackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrackServiceUnitTest {
    @Mock
    private TrackDAO trackDAO;

    @InjectMocks
    private TrackService trackService;

    //@InjectMocks
    //protected PriceProvider priceProvider;

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
        List<Track> tracks = inicialize();

        Mockito.when(trackDAO.addTrack(tracks.get(1))).thenReturn(tracks.get(1));

        Track newTrack = trackService.addTrack(tracks.get(1));

        Mockito.verify(trackDAO).addTrack(tracks.get(1));
    }

    @Test
    public void deleteTrackTestOk() {
        List<Track> tracks = inicialize();
        Mockito.when(trackDAO.deleteTrack(tracks.getFirst().getIdTrack())).thenReturn(true);

        boolean result = trackService.deleteTrack(tracks.getFirst().getIdTrack());
        assertTrue(result);

        Mockito.verify(trackDAO).deleteTrack(tracks.getFirst().getIdTrack());
    }

    @Test
    public void deleteTrackTestFail() {
        Mockito.when(trackDAO.deleteTrack(999)).thenReturn(false);

        boolean result = trackService.deleteTrack(999);
        assertFalse(result);

        Mockito.verify(trackDAO).deleteTrack(999);
    }

    @Test
    public void updateTrackOk() {
        List<Track> tracks = inicialize();

        Mockito.when(trackDAO.updateTrack(tracks.get(1))).thenReturn(true);

        boolean result = trackService.updateTrack(tracks.get(1));
        assertTrue(result);

        Mockito.verify(trackDAO).updateTrack(tracks.get(1));
    }

    /*
    @Test
    public void getTrackById() {
        List<Track> tracks = inicialize();

        Mockito.when(trackDAO.getTrackById(1)).thenReturn(tracks.get(1));

        Track result = trackService.getTrackById(1);
        assertNotNull(result);

        Mockito.verify(trackDAO).getTrackById(1);
    }*/
}
