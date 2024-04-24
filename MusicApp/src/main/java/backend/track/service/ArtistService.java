package backend.track.service;

import backend.track.dao.ArtistDAO;
import backend.track.models.Artist;
import backend.track.models.Track;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    private ArtistDAO artistDAO;

    public ArtistService(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    public Artist addArtist(Artist artist) {
        return artistDAO.addArtist(artist);
    }

    public boolean deleteArtist(int idArtist){
        return artistDAO.deleteArtist(idArtist);
    }

    public boolean updateArtist(Artist artist){
        return artistDAO.updateArtist(artist);
    }

    public Artist getArtistById(int idArtist) {
        return artistDAO.getArtistById(idArtist);
    }

    public Artist getArtistByName(String name){
        return artistDAO.getArtistByName(name);
    }

    public List<Artist> getAllArtists() {
        return  artistDAO.getAllArtists();
    }
}
