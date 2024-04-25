package backend.track.price;

import backend.track.models.Track;

public interface PriceProvider {
    void addPriceToTrack(Track track);
}
