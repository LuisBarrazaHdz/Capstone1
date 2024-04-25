package backend.track.price;

import backend.track.models.Track;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("localprice")
public class InMemoryPriceProvider implements PriceProvider{
    @Override
    public void addPriceToTrack(Track track) {
        track.setPrice(5);
    }
}
