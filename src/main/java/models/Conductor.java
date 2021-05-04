package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Conductor {
    private String id;
    private String nombre;
    private Integer victorias;
    private String posicionPodio;
    private Carro carro;

    public Conductor(String id, String nombre, Carro carro) {
        this.id = id;
        this.nombre = nombre;
        this.carro = carro;
    }
}
