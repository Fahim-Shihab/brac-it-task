package brac.it.sample.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record BatteryDto(
        @NotNull
                @Size(min = 0, max = 255)
        String name,
        @Size(max = 10)
        String postcode,
        @NotNull
        Integer wattCapacity
) {
}
