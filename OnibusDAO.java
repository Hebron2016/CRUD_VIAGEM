package LABBD.crudviagem;

import java.util.List;

public interface OnibusDAO {
    public void adicionar(Onibus on) throws OnibusException;
    public List<Onibus> pesquisarTodos() throws OnibusException;
    public List<Onibus> pesquisarPorPlaca(String placa) throws OnibusException;
    public void remover(String placa) throws OnibusException;
    public void atualizar(Onibus on) throws OnibusException;
}
