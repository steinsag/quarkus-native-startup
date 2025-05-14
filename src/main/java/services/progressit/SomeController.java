package services.progressit;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/are-you-ready")
public class SomeController {

    @POST
    @Produces(TEXT_PLAIN)
    public String areYouReady(
            @Valid
            @NotNull
            ActivityDto body
    ) {
        return "Let's get started with %s!".formatted(body.activity());
    }
}
