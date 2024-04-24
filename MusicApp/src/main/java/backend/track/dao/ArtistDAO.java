package backend.track.dao;

import backend.track.models.Artist;
import java.util.List;

public interface ArtistDAO {
    Artist addArtist(Artist artist);
    boolean deleteArtist(int idArtist);
    boolean updateArtist(Artist artist);
    Artist getArtistById(int idArtist);
    Artist getArtistByName(String name);
    List<Artist> getAllArtists();
}
