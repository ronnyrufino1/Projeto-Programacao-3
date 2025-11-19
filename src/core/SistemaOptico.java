package core;

import strategy.Absorcao;
import strategy.Reflexao;
import strategy.Refracao;

import java.util.ArrayList;
import java.util.List;

public class SistemaOptico {
    private List<EstruturaOpticaAbstract> components = new ArrayList<>();

    public void add(Reflexao component) {
        components.add(component);
    }

    //@Override
    public void processo(FeixeLuz feixeLuz) {
        for (EstruturaOpticaAbstract component : components) {
            component.processo(feixeLuz);
        }
    }

    //@Override
    public String getTipo() {
        return "Sistema Óptico";
    }

    //@Override
    public String getName() {
        return "Sistema Completo";
    }

    public void add(Refracao refracao) {
    }

    public void add(Absorcao absorcao) {
    }
}
