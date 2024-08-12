package LABBD.crudviagem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViagemControl {
        private ObservableList<Viagem> lista = 
        FXCollections.observableArrayList();

        private IntegerProperty codigo = new SimpleIntegerProperty(0);
        private IntegerProperty horaSaida = new SimpleIntegerProperty(0);
        private IntegerProperty horaChegada = new SimpleIntegerProperty(0);
        private StringProperty partida = new SimpleStringProperty("");
        private StringProperty destino = new SimpleStringProperty("");

        private ViagemDAO viagemDao = null;

        public ViagemControl()throws ViagemException{
            try {
                viagemDao = new ViagemDAOImpl();
            } catch (Exception e) {
                throw new ViagemException(e);
            }
        }
        public void excluir(int codigo) throws ViagemException{
            viagemDao.remover(codigo);
        }
        public void adicionar() throws ViagemException{
            Viagem o = new Viagem();
            o.setCodigo(codigo.get());
            o.setHora_saida(horaSaida.get());
            o.setHora_chegada(horaChegada.get());
            viagemDao.adicionar(o);
        }
        public void pesquisarPorPartida() throws ViagemException{
            lista.clear();
            lista.addAll(viagemDao.pesquisarPorPartida(partida.get()));
        }

        public ObservableList<Viagem> getLista(){
            return lista;
        }
        public IntegerProperty codigoProperty(){
            return codigo;
        }
        public IntegerProperty horaSaidaProperty(){
            return horaSaida;
        }
        public IntegerProperty horaChegadaProperty(){
            return horaChegada;
        }
        public StringProperty partidaProperty(){
            return partida;
        }
        public StringProperty destinoProperty(){
            return destino;
        }
}
