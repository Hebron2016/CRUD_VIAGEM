package LABBD.crudviagem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OnibusControl {
        private ObservableList<Onibus> lista = 
        FXCollections.observableArrayList();

        private StringProperty placa = new SimpleStringProperty("");
        private StringProperty marca = new SimpleStringProperty("");
        private IntegerProperty ano = new SimpleIntegerProperty(0);

        private OnibusDAO onibusDao = null;

        public OnibusControl()throws OnibusException{
            try {
                onibusDao = new OnibusDAOImpl();
            } catch (Exception e) {
                throw new OnibusException(e);
            }
        }
        public void excluir(String placa) throws OnibusException{
            onibusDao.remover(placa);
        }
        public void adicionar() throws OnibusException{
            Onibus o = new Onibus();
            o.setPlaca(placa.get());
            o.setMarca(marca.get());
            o.setAno(ano.get());
            onibusDao.adicionar(o);
        }
        public void pesquisarPorNome() throws OnibusException{
            lista.clear();
            lista.addAll(onibusDao.pesquisarPorPlaca(placa.get()));
        }

        public ObservableList<Onibus> getLista(){
            return lista;
        }
        public StringProperty placaProperty(){
            return placa;
        }
        public StringProperty marcaProperty(){
            return marca;
        }
        public IntegerProperty anoProperty(){
            return ano;
        }
}
