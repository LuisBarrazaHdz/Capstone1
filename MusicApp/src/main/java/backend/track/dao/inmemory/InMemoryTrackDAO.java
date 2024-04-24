package backend.track.dao.inmemory;

import backend.track.dao.TrackDAO;
import backend.track.models.DurationType;
import backend.track.models.MediaType;
import backend.track.models.Track;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryTrackDAO implements TrackDAO {
    private Map<Integer, Track> tracks = new HashMap<>();
    private AtomicInteger nextId = new AtomicInteger(1);
    @Override
    public Track addTrack(Track track) {
        track.setIdTrack(nextId.getAndIncrement());
        this.tracks.put(track.getIdTrack(), track);
        return track;
    }

    @Override
    public boolean deleteTrack(int idTrack) {
        return this.tracks.remove(idTrack) != null;
    }

    @Override
    public boolean updateTrack(Track track) {
        return this.tracks.replace(track.getIdTrack(), track) != null;
    }

    @Override
    public Track getTrackById(int idTrack) {
        return this.tracks.get(idTrack);
    }

    @Override
    public List<Track> getTracksByMediaType(MediaType mediaType) {
        return new ArrayList<>(this.tracks.values()).stream().filter(truck -> truck.getMediaType() == mediaType).collect(Collectors.toList());
    }

    @Override
    public List<Track> getTracksByYear(int year) {
        return new ArrayList<>(this.tracks.values()).stream().filter(track -> track.getIssueDate().getYear() == year).collect(Collectors.toList());
    }

    @Override
    public List<Track> getTracksByArtist(int idArtist) {
        return new ArrayList<>(this.tracks.values()).stream().filter(f -> f.getArtists().stream().anyMatch(a -> a.getIdArtist()==idArtist)).collect(Collectors.toList());
    }

    @Override
    public List<Track> getTracksByDuration(DurationType durationType, LocalTime duration) {
        Predicate<Track> predicateDuration;
        switch(durationType) {
            case Longer:
                predicateDuration = (f) -> f.getDuration().isAfter(duration);
                break;
            case Shorter:
                predicateDuration = (f) -> f.getDuration().isBefore(duration);
                break;
            case Equal:
                predicateDuration= (f) -> f.getDuration().equals(duration);
                break;
            default:
                return null;
        }
        return new ArrayList<>(this.tracks.values()).stream().filter(predicateDuration).collect(Collectors.toList());
    }
}
