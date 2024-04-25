package backend.price.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/price")
public class PriceController {

    private int lowerLimit = 1;
    private int higherLimit = 100;

    @GetMapping(path = "holamundo")
    public String getHolaMundo() {
        return "Hola mundo :D!!!";
    }

    @GetMapping("getPrice")
    public BigDecimal getPrice() {
        double result = ThreadLocalRandom.current().nextDouble(lowerLimit, higherLimit);
        return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
    }
}
