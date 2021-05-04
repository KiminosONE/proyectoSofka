package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resultado {
    private String id;
    private String nombreCarrera;
    private Integer numeroCarriles;
    private Integer distanciaTotal;
    private List<Conductor> conductores;
}
