package nl.wjglerum;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VirtualBeerService {

    public Beer getFromDraft() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Beer("Virtual Guinness");
    }
}
