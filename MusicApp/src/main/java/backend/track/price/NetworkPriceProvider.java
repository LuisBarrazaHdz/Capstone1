package backend.track.price;

import backend.track.models.Track;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Profile("networkprice")
public class NetworkPriceProvider implements PriceProvider{

    @Override
    public void addPriceToTrack(Track track) {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:10001/price/getPrice")
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .build();

        ResponseEntity<Double> response = restClient.get()
                .uri("http://localhost:10001/price/getPrice")
                .retrieve()
                .toEntity(Double.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            Double price =  response.getBody();
            if(price != null) {
                track.setPrice(price);
            }
        }
    }
}
