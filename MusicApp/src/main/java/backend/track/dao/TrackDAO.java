package backend.track.dao;

import backend.track.models.Artist;
import backend.track.models.DurationType;
import backend.track.models.MediaType;
import backend.track.models.Track;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public interface TrackDAO {
    Track addTrack(Track track);
    boolean deleteTrack(int idTrack);
    boolean updateTrack(Track track);
    Track getTrackById(int idTrack);
    List<Track> getTracksByMediaType(MediaType mediaType);
    List<Track> getTracksByYear(int year);
    List<Track> getTracksByArtist(int idArtist);
    List<Artist> getArtistByTrack(int idTrack);
    List<Track> getTracksByDuration(DurationType durationType, LocalTime duration);
}
