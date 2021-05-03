package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pista {
    private List<Carril> carriles;
    private Integer distanciaTotal;
    private Integer numeroCarriles;
}
