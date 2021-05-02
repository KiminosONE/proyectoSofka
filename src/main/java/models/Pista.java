package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Pista {
    private List<Carril> carriles;
    private Integer distanciaTotal;
}
