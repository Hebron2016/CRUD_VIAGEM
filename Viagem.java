package LABBD.crudviagem;

public class Viagem {
    private int codigo;
    private int hora_saida;
    private int hora_chegada;
    private String partida;
    private String destino;

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getHora_saida() {
        return hora_saida;
    }
    public void setHora_saida(int hora_saida) {
        this.hora_saida = hora_saida;
    }
    public int getHora_chegada() {
        return hora_chegada;
    }
    public void setHora_chegada(int hora_chegada) {
        this.hora_chegada = hora_chegada;
    }
    public String getPartida() {
        return partida;
    }
    public void setPartida(String partida) {
        this.partida = partida;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
}
