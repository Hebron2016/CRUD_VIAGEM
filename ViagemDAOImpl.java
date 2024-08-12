package LABBD.crudviagem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViagemDAOImpl extends DBconnection implements ViagemDAO {
    public ViagemDAOImpl(){
        super();
    }

    @Override
    public void adicionar(Viagem vg) throws ViagemException {
        String sql = "INSERT INTO Viagem(codigo, horaSaida, horaChegada, partida, destino) VALUES (?, ?, ?,?,?)";

            PreparedStatement pstm;
            try {
                pstm = con.prepareStatement(sql);
                pstm.setInt(3, vg.getCodigo());
                pstm.setInt(3, vg.getHora_saida());
                pstm.setInt(3, vg.getHora_chegada());
                pstm.setString(1, vg.getPartida());
                pstm.setString(2, vg.getDestino());
                pstm.executeUpdate();
            }  catch (SQLException e) {
                e.printStackTrace();
            }
        
    }

    @Override
    public List<Viagem> pesquisarTodos() throws ViagemException {
        List<Viagem> lista = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Viagem ";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Select executado com sucesso");

			while (rs.next()) {
				Viagem c = new Viagem();
                c.setCodigo(Integer.parseInt(rs.getString("codigo")));
                c.setHora_saida(Integer.parseInt(rs.getString("horaSaida")));
                c.setHora_chegada(Integer.parseInt(rs.getString("horaChegada")));
				c.setPartida(rs.getString("partida"));
				c.setDestino(rs.getString("destino"));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
    }

    @Override
    public List<Viagem> pesquisarPorPartida(String partida) throws ViagemException {
        List<Viagem> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM viagem " + "WHERE partida LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + partida + "%");
			ResultSet rs = stmt.executeQuery();
			System.out.println("Select por placa executado com sucesso");

			while (rs.next()) {
				Viagem c = new Viagem();
                c.setCodigo(Integer.parseInt(rs.getString("codigo")));
                c.setHora_saida(Integer.parseInt(rs.getString("horaSaida")));
                c.setHora_chegada(Integer.parseInt(rs.getString("horaChegada")));
				c.setPartida(rs.getString("partida"));
				c.setDestino(rs.getString("destino"));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
    }

    @Override
    public void remover(int codigo) throws ViagemException {
		try {
			String sql = "DELETE FROM Viagem " + "WHERE codigo=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, Integer.valueOf(codigo));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
    

    @Override
    public void atualizar(Viagem vg) throws ViagemException {
        try {
			String sql = "UPDATE Viagem SET horaSaida=?, horaChegada=?, partida=?, destino=?" + "WHERE codigo=?";

			PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(2, vg.getHora_saida());
			stmt.setInt(2, vg.getHora_chegada());
			stmt.setString(1, vg.getPartida());
            stmt.setString(1, vg.getDestino());
			stmt.setInt(3, Integer.valueOf(vg.getCodigo()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
