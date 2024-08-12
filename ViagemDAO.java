package LABBD.crudviagem;

import java.util.List;

public interface ViagemDAO {
    public void adicionar(Viagem vg) throws ViagemException;
    public List<Viagem> pesquisarTodos() throws ViagemException;
    public List<Viagem> pesquisarPorPartida(String partida) throws ViagemException;
    public void remover(int codigo) throws ViagemException;
    public void atualizar(Viagem vg) throws ViagemException;
}
