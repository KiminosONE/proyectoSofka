package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Conductor {
    private String nombre;
    private Integer victorias;
    private String posicionPodio;
    private Carro carro;
}
