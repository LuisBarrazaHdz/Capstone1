package backend.track.service;

import backend.track.dao.TrackDAO;
import backend.track.models.Artist;
import backend.track.models.DurationType;
import backend.track.models.MediaType;
import backend.track.models.Track;
import backend.track.price.PriceProvider;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Service
public class TrackService {
    private TrackDAO trackDAO;

    private PriceProvider priceProvider;

    public TrackService(TrackDAO trackDAO, PriceProvider priceProvider) {
        this.trackDAO = trackDAO;
        this.priceProvider = priceProvider;
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
        Track track = trackDAO.getTrackById(idTrack);
        if(track!=null)
            priceProvider.addPriceToTrack(track);
        return track;
    }

    public List<Track> getTracksByMediaType(MediaType mediaType) {
        List<Track> tracks = trackDAO.getTracksByMediaType(mediaType);
        tracks.forEach(priceProvider::addPriceToTrack);
        return tracks;
    }

    public List<Track> getTracksByYear(int year){
        List<Track> tracks = trackDAO.getTracksByYear(year);
        tracks.forEach(priceProvider::addPriceToTrack);
        return tracks;
    }

    public List<Track> getTracksByArtist(int idArtist) {
        List<Track> tracks = trackDAO.getTracksByArtist(idArtist);
        tracks.forEach(priceProvider::addPriceToTrack);
        return tracks;
    }

    public List<Artist> getArtistByTrack(int idTrack){
        List<Artist> artists = trackDAO.getArtistByTrack(idTrack);
        return artists;
    }

    public List<Track> getTracksByDuration(DurationType durationType, LocalTime duration) {
        List<Track> tracks = trackDAO.getTracksByDuration(durationType, duration);
        tracks.forEach(priceProvider::addPriceToTrack);
        return tracks;
    }
}
