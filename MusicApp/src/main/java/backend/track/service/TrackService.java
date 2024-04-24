package backend.track.service;

import backend.track.dao.TrackDAO;
import backend.track.models.DurationType;
import backend.track.models.MediaType;
import backend.track.models.Track;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Service
public class TrackService {
    private TrackDAO trackDAO;

    public TrackService(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public Track addTrack(Track track) {
        return trackDAO.addTrack(track);
    }

    public boolean deleteTrack(int idTrack) {
        return trackDAO.deleteTrack(idTrack);
    }

    public boolean updateTrack(Track track) {
        return trackDAO.updateTrack(track);
    }

    public Track getTrackById(int idTrack) {
        return trackDAO.getTrackById(idTrack);
    }

    public List<Track> getTracksByMediaType(MediaType mediaType) {
        return trackDAO.getTracksByMediaType(mediaType);
    }

    public List<Track> getTracksByYear(int year){
        return trackDAO.getTracksByYear(year);
    }

    public List<Track> getTracksByArtist(int idArtist) {
        return trackDAO.getTracksByArtist(idArtist);
    }

    public List<Track> getTracksByDuration(DurationType durationType, LocalTime duration) {
        return  trackDAO.getTracksByDuration(durationType, duration);
    }
}
