package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ConfiguracionJuego {
    private Pista pista;
    private String nombreCarrera;
}
