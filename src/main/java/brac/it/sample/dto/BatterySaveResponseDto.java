package brac.it.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatterySaveResponseDto {
    Integer id;
    String name;
    String postCode;
    Integer wattCapacity;
}
