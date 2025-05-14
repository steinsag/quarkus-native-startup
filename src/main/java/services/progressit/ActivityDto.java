package services.progressit;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ActivityDto(
        @NotNull
        @Size(min = 3, max = 20)
        String activity
) {
}
