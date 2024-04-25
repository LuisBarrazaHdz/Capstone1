package backend.track.controller;

import backend.track.models.Artist;
import backend.track.models.Track;
import backend.track.service.ArtistService;
import backend.track.service.TrackService;
import backend.track.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    private ArtistService artistService;

    private TrackService trackService;
    private UriCreator uriCreator;

    public ArtistController(ArtistService artistService, TrackService trackService, UriCreator uriCreator) {
        this.artistService = artistService;
        this.trackService = trackService;
        this.uriCreator = uriCreator;
    }

    @GetMapping(path = "holamundo")
    public String getHolaMundo() {
        return "Hola mundo :D!!!";
    }

    @PostMapping
    public ResponseEntity<?> addArtist(@RequestBody Artist artist) {
        Artist addNewArtist = artistService.addArtist(artist);

        URI uri = uriCreator.getUriFor(addNewArtist.getIdArtist());

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{idArtist:\\d+}")
    public ResponseEntity<?> deleteAdopter(@PathVariable("idArtist") int idArtist) {
        boolean result = artistService.deleteArtist(idArtist);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No exist idArtist: " + idArtist);
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateAdopter(@RequestBody Artist artist) {
        boolean result = artistService.updateArtist(artist);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No exist artist id: " + artist.getIdArtist());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getArtistById/{idArtist:\\d+}")
    public ResponseEntity<?> getArtistById(@PathVariable("idArtist") int idArtist) {
        Artist artist =  artistService.getArtistById(idArtist);
        if(artist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No found artist id: " + idArtist);
        }
        return ResponseEntity.ok(artist);
    }

    @GetMapping("/getArtistByName/{name}")
    public ResponseEntity<?> getArtistByName(@PathVariable("name") String name) {
        Artist artist =  artistService.getArtistByName(name);
        if(artist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No found artist name: " + name);
        }
        return ResponseEntity.ok(artist);
    }

    @GetMapping("/getTracksByArtist/{idArtist}")
    public ResponseEntity<?> getTracksByArtist(@PathVariable("idArtist") int idArtist) {
        List<Track> tracks = trackService.getTracksByArtist(idArtist);
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("getAllArtists")
    public ResponseEntity<?> getAllArtists() {
        List<Artist> artist = artistService.getAllArtists();
        return ResponseEntity.ok(artist);
    }

}
