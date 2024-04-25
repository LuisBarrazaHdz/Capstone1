package backend.track.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@Builder
public class Track {
    private int idTrack;
    private String title;
    private String album;
    private List<Artist> artists;
    private LocalDate issueDate;
    private MediaType mediaType;
    private LocalTime duration;
    private double price;

    @Override
    public String toString() {
        return "Track{" +
                "idTrack=" + idTrack +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", issueDate=" + issueDate +
                ", mediaType=" + mediaType +
                ", duration=" + duration +
                '}';
    }
}
