package ManueleSeretti.progettou5w2.Payloads;

import ManueleSeretti.progettou5w2.Entities.StatoDevice;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record newDeviceDTO(
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        @Size(min = 3, max = 30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String name,

        @NotEmpty(message = "L'username è un campo obbligatorio!")
        String username,
        @NotNull(message = "L'Id dello user è un campo obbligatorio!")
        int userID,

        StatoDevice stato


) {
}