package backend.track.service.unittest;

import backend.track.dao.ArtistDAO;
import backend.track.dao.TrackDAO;
import backend.track.models.Artist;
import backend.track.models.MediaType;
import backend.track.models.Track;
import backend.track.service.ArtistService;
import backend.track.service.TrackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceUnitTest {

    @Mock
    private ArtistDAO artistDAO;

    @InjectMocks
    private ArtistService artistService;

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
        List<Artist> artists = inicialize();

        Mockito.when(artistDAO.addArtist(artists.get(1))).thenReturn(artists.get(1));

        Artist newArtist = artistService.addArtist(artists.get(1));

        Mockito.verify(artistDAO).addArtist(artists.get(1));
    }

    @Test
    public void deleteArtistOk() {
        List<Artist> artists = inicialize();
        Mockito.when(artistDAO.deleteArtist(artists.getFirst().getIdArtist())).thenReturn(true);

        boolean result = artistService.deleteArtist(artists.getFirst().getIdArtist());
        assertTrue(result);

        Mockito.verify(artistDAO).deleteArtist(artists.getFirst().getIdArtist());
    }

    @Test
    public void deleteArtistFail() {
        Mockito.when(artistDAO.deleteArtist(999)).thenReturn(false);

        boolean result = artistService.deleteArtist(999);
        assertFalse(result);

        Mockito.verify(artistDAO).deleteArtist(999);
    }

    @Test
    public void updateTrackOk() {
        List<Artist> artists = inicialize();

        Mockito.when(artistDAO.updateArtist(artists.get(1))).thenReturn(true);

        boolean result = artistService.updateArtist(artists.get(1));
        assertTrue(result);

        Mockito.verify(artistDAO).updateArtist(artists.get(1));
    }
}
