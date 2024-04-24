package backend.track.dao.inmemory;

import backend.track.dao.ArtistDAO;
import backend.track.models.Artist;
import backend.track.models.Track;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryArtistDAO implements ArtistDAO {
    private Map<Integer, Artist> artistMap = new HashMap<>();
    private AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public Artist addArtist(Artist artist) {
        artist.setIdArtist(nextId.getAndIncrement());
        this.artistMap.put(artist.getIdArtist(), artist);
        return artist;
    }

    @Override
    public boolean deleteArtist(int idArtist) {
        return this.artistMap.remove(idArtist) != null;
    }

    @Override
    public boolean updateArtist(Artist artist) {
        return this.artistMap.replace(artist.getIdArtist(), artist) != null;
    }

    @Override
    public Artist getArtistById(int idArtist) {
        return this.artistMap.get(idArtist);
    }

    @Override
    public Artist getArtistByName(String name) {
        return this.artistMap.values().stream().filter(artist -> artist.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @Override
    public List<Artist> getAllArtists() {
        return new ArrayList<>(artistMap.values());
    }
}
