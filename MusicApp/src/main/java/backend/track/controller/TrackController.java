package backend.track.controller;

import backend.track.models.Artist;
import backend.track.models.DurationType;
import backend.track.models.MediaType;
import backend.track.models.Track;
import backend.track.service.ArtistService;
import backend.track.service.TrackService;
import backend.track.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/track")
public class TrackController {
    private TrackService trackService;

    private UriCreator uriCreator;

    public TrackController(TrackService trackService, UriCreator uriCreator) {
        this.trackService = trackService;
        this.uriCreator = uriCreator;
    }

    @GetMapping(path = "holamundo")
    public String getHolaMundo() {
        return "Hola mundo :D!!!";
    }

    @PostMapping
    public ResponseEntity<?> addAdopter(@RequestBody Track track) {
        Track addNewTrack = trackService.addTrack(track);

        URI uri = uriCreator.getUriFor(addNewTrack.getIdTrack());

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{idTrack:\\d+}")
    public ResponseEntity<?> deleteAdopter(@PathVariable("idTrack") int idTrack) {
        boolean result = trackService.deleteTrack(idTrack);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No exist Track: " + idTrack);
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateAdopter(@RequestBody Track track) {
        boolean result = trackService.updateTrack(track);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No exist track id: " + track.getIdTrack());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getTrackById/{idTrack:\\d+}")
    public ResponseEntity<?> getAdopterById(@PathVariable("idTrack") int idTrack) {
        Track track =  trackService.getTrackById(idTrack);
        if(track == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No found track id: " + idTrack);
        }
        return ResponseEntity.ok(track);
    }

    @GetMapping("/getTracksByMediaType/{mediaType}")
    public ResponseEntity<?> getTracksByMediaType(@PathVariable("mediaType") MediaType mediaType) {
        List<Track> tracks = trackService.getTracksByMediaType(mediaType);
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/getTracksByYear/{year}")
    public ResponseEntity<?> getTracksByYear(@PathVariable("year") int year) {
        List<Track> tracks = trackService.getTracksByYear(year);
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/getArtistByTrack/{idTrack}")
    public ResponseEntity<?> getArtistByTrack(@PathVariable("idTrack") int idTrack) {
        List<Artist> artists = trackService.getArtistByTrack(idTrack);
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/getTracksByDuration/{durationType}/{duration}")
    public ResponseEntity<?> getTracksByDuration(@PathVariable("durationType") DurationType durationType,
                                                 @PathVariable("duration") LocalTime duration) {
        List<Track> tracks = trackService.getTracksByDuration(durationType, duration);
        return ResponseEntity.ok(tracks);
    }
}
