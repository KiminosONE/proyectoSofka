package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Carro {
    private String color;
    private String marca;
    private String modelo;
    private Conductor conductor;
}
