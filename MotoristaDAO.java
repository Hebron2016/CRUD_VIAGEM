package LABBD.crudviagem;

import java.util.List;

public interface MotoristaDAO {
    public void adicionar(Motorista mt) throws MotoristaException;
    public List<Motorista> pesquisarTodos() throws MotoristaException;
    public List<Motorista> pesquisarPorNome(String nome) throws MotoristaException;
    public void remover(int codigo) throws MotoristaException;
    public void atualizar(Motorista mt) throws MotoristaException;

}
