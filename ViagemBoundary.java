package LABBD.crudviagem;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class ViagemBoundary {
    private TextField txtCodigo = new TextField();
    private TextField txtHoraSaida = new TextField();
    private TextField txtHoraChegada = new TextField();
    private TextField txtPartida = new TextField();
    private TextField txtDestino = new TextField();


    private Label lblCodigo = new Label("Codigo:");
    private Label lblHoraSaida = new Label("HoraSaida:");
    private Label lblHoraChegada = new Label("HoraChegada:");
    private Label lblPartida = new Label("Partida:");
    private Label lblDestino = new Label("Destino:");

    private TableView<Viagem> tableViagem = new TableView<>();

    private ViagemControl control = null;

    public void start(Stage stage){
        try {
            control = new ViagemControl();
        } 
        catch (ViagemException e) {
            alert("Erro ao iniciar o controle");
        }

        BorderPane panePrincipal = new BorderPane();
        GridPane grid = new GridPane();

        Scene scn = new Scene(panePrincipal, 400,600);

        panePrincipal.setTop(grid);
        panePrincipal.setCenter(tableViagem);

        grid.add(lblCodigo, 0, 0);
        grid.add(txtCodigo, 1, 0);
        grid.add(lblHoraSaida, 0, 1);
        grid.add(txtHoraSaida, 1, 1);
        grid.add(lblHoraChegada, 0, 2);
        grid.add(txtHoraChegada, 1, 2);
        grid.add(lblHoraSaida, 0, 3);
        grid.add(txtHoraSaida, 1, 3);
        grid.add(lblPartida, 0, 4);
        grid.add(txtPartida, 1, 4);
        grid.add(lblDestino, 0, 5);
        grid.add(txtDestino, 1, 5);

        Button btnAdicionar = new Button("Adcionar");
        btnAdicionar.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                try{
                    control.adicionar();
                }catch(Exception err){
                    alert("Erro ao adicionar");
                }

            }
        });

        Button btnPesquisar = new Button("Pesquisar");
        btnAdicionar.addEventFilter(ActionEvent.ANY, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                try{
                    control.pesquisarPorPartida();
                }catch(Exception err){
                    alert("Erro ao pesquisar por Nome");
                }
            }
        });
        grid.add(btnAdicionar,0,3);
        grid.add(btnPesquisar,1,3);

        createTableView();
        generateBindings();

        stage.setScene(scn);
        stage.show();

    }
    public void generateBindings() { 
        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(txtCodigo.textProperty(), control.codigoProperty(), converter);
        Bindings.bindBidirectional(txtHoraSaida.textProperty(), control.horaSaidaProperty(), converter);
        Bindings.bindBidirectional(txtHoraChegada.textProperty(), control.horaChegadaProperty(), converter);
        Bindings.bindBidirectional(txtPartida.textProperty(), control.partidaProperty());
        Bindings.bindBidirectional(txtDestino.textProperty(), control.destinoProperty());

    }

    public void alert(String msg){
        Alert alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    public void createTableView(){
        TableColumn<Viagem, Integer> col1 = new TableColumn("Codigo");
        col1.setCellValueFactory( new PropertyValueFactory<>("codigo"));
        TableColumn<Viagem, Integer> col2 = new TableColumn("HoraSaida");
        col2.setCellValueFactory( new PropertyValueFactory<>("horaSaida"));
        TableColumn<Viagem, Integer> col3 = new TableColumn("HoraChegada");
        col3.setCellValueFactory( new PropertyValueFactory<>("horaChegada"));
        TableColumn<Viagem, String> col4 = new TableColumn("Partida");
        col4.setCellValueFactory( new PropertyValueFactory<>("partida"));
        TableColumn<Viagem, String> col5 = new TableColumn("Destino");
        col5.setCellValueFactory( new PropertyValueFactory<>("destino"));

        TableColumn<Viagem, Void> colAcoes = new TableColumn<>("Ações");
        Callback<TableColumn<Viagem, Void>, TableCell<Viagem, Void>> callback = new Callback<>() { 
            public TableCell<Viagem, Void> call(TableColumn<Viagem, Void> coluna) {
                TableCell<Viagem, Void> tc = new TableCell<>() {
                    final Button btnExcluir = new Button("Excluir");
                    {
                        btnExcluir.setOnAction(event -> { 
                            Viagem a1 = table.getItems().get(getIndex());
                            control.excluir(a1);
                        });
                    }
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnExcluir);
                        }
                    }
                };
                return tc;
            }
        };

        tableViagem.setItems( control.getLista() );
        colAcoes.setCellFactory( callback );

        tableViagem.getColumns().addAll(col1, col2, col3,col4,col5, colAcoes);
    }
}
