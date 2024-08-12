package LABBD.crudviagem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MotoristaControl {
        private ObservableList<Motorista> lista = 
        FXCollections.observableArrayList();

        private IntegerProperty codigo = new SimpleIntegerProperty(0);
        private StringProperty nome = new SimpleStringProperty("");
        private StringProperty naturalidade = new SimpleStringProperty("");

        private MotoristaDAO motoristaDao = null;

        public MotoristaControl()throws MotoristaException{
            try {
                motoristaDao = new MotoristaDAOImpl();
            } catch (Exception e) {
                throw new MotoristaException(e);
            }
        }
        public void excluir(int codigo) throws MotoristaException{
            motoristaDao.remover(codigo);
        }
        public void adicionar() throws MotoristaException{
            Motorista m = new Motorista();
            m.setCodigo(codigo.get());
            m.setNome(nome.get());
            m.setNaturalidade(naturalidade.get());
            motoristaDao.adicionar(m);
        }
        public void pesquisarPorNome() throws MotoristaException{
            lista.clear();
            lista.addAll(motoristaDao.pesquisarPorNome(nome.get()));
        }

        public ObservableList<Motorista> getLista(){
            return lista;
        }
        public IntegerProperty codigoProperty(){
            return codigo;
        }
        public StringProperty nomeProperty(){
            return nome;
        }
        public StringProperty naturalidadeProperty(){
            return naturalidade;
        }
}
