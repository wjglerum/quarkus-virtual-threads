package nl.wjglerum;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BlockingBeerService {

    public Beer getFromDraft() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Beer("Blocking Guinness");
    }
}
