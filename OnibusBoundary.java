package LABBD.crudviagem;

import javafx.application.Application;
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

public class OnibusBoundary extends Application {
    private TextField txtPlaca = new TextField();
    private TextField txtMarca = new TextField();
    private TextField txtAno = new TextField();

    private Label lblPlaca = new Label("Placa:");
    private Label lblMarca = new Label("Nome:");
    private Label lblAno = new Label("Ano:");

    private TableView<Onibus> tableOnibus = new TableView<>();

    private OnibusControl control = null;

    public void start(Stage stage){
        try {
            control = new OnibusControl();
        } 
        catch (OnibusException e) {
            alert("Erro ao iniciar o controle");
        }

        BorderPane panePrincipal = new BorderPane();
        GridPane grid = new GridPane();

        Scene scn = new Scene(panePrincipal, 400,600);

        panePrincipal.setTop(grid);
        panePrincipal.setCenter(tableOnibus);

        grid.add(lblPlaca, 0, 0);
        grid.add(txtPlaca, 1, 0);
        grid.add(lblMarca, 0, 1);
        grid.add(txtMarca, 1, 1);
        grid.add(lblAno, 0, 2);
        grid.add(txtAno, 1, 2);

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
                    control.pesquisarPorNome();
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
        Bindings.bindBidirectional(txtPlaca.textProperty(), control.placaProperty());
        Bindings.bindBidirectional(txtMarca.textProperty(), control.marcaProperty());
        Bindings.bindBidirectional(txtAno.textProperty(), control.anoProperty(), converter);

    }

    public void alert(String msg){
        Alert alert = new Alert(AlertType.INFORMATION,msg,ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    public void createTableView(){
        TableColumn<Onibus, String> col1 = new TableColumn("Placa");
        col1.setCellValueFactory( new PropertyValueFactory<>("placa"));
        TableColumn<Onibus, String> col2 = new TableColumn("Marca");
        col2.setCellValueFactory( new PropertyValueFactory<>("marca"));
        TableColumn<Onibus, Integer> col3 = new TableColumn("Ano");
        col3.setCellValueFactory( new PropertyValueFactory<>("ano"));

        TableColumn<Onibus, Void> colAcoes = new TableColumn<>("Ações");
        Callback<TableColumn<Onibus, Void>, TableCell<Onibus, Void>> callback = new Callback<>() { 
            public TableCell<Onibus, Void> call(TableColumn<Onibus, Void> coluna) {
                TableCell<Onibus, Void> tc = new TableCell<>() {
                    final Button btnExcluir = new Button("Excluir");
                    {
                        btnExcluir.setOnAction(event -> { 
                            Onibus a1 = table.getItems().get(getIndex());
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

        tableOnibus.setItems( control.getLista() );
        colAcoes.setCellFactory( callback );

        tableOnibus.getColumns().addAll(col1, col2, col3, colAcoes);
    }
}
