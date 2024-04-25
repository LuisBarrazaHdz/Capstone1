package backend.track.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Artist {
    private int idArtist;
    private  String name;

    public Artist(int idArtist, String name) {
        this.idArtist = idArtist;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "idArtist=" + idArtist +
                ", name='" + name + '\'' +
                '}';
    }
}
