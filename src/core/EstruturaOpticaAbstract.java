package core;

public abstract class EstruturaOpticaAbstract {
    protected String nome;

    public EstruturaOpticaAbstract(String name) {
        this.nome = nome;
    }

    protected EstruturaOpticaAbstract() {
    }

    public abstract void processo(FeixeLuz feixeLuz);
    public abstract String getTipo();

    public abstract String getNome();
}
