package LABBD.crudviagem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OnibusDAOImpl extends DBconnection implements OnibusDAO {
    public OnibusDAOImpl(){
        super();
    }

    @Override
    public void adicionar(Onibus on) throws OnibusException {
        String sql = "INSERT INTO Onibus(placa, marca, ano) VALUES (?, ?, ?)";

            PreparedStatement pstm;
            try {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, on.getPlaca());
                pstm.setString(2, on.getMarca());
                pstm.setInt(3, on.getAno());
                pstm.executeUpdate();
            }  catch (SQLException e) {
                e.printStackTrace();
            }
        
    }

    @Override
    public List<Onibus> pesquisarTodos() throws OnibusException {
        List<Onibus> lista = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Onibus ";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Select executado com sucesso");

			while (rs.next()) {
				Onibus c = new Onibus();
				c.setPlaca(rs.getString("placa"));
				c.setMarca(rs.getString("marca"));
				c.setAno(Integer.parseInt(rs.getString("ano")));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
    }

    @Override
    public List<Onibus> pesquisarPorPlaca(String placa) throws OnibusException {
        List<Onibus> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Onibus " + "WHERE placa LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + placa + "%");
			ResultSet rs = stmt.executeQuery();
			System.out.println("Select por placa executado com sucesso");

			while (rs.next()) {
				Onibus c = new Onibus();
				c.setPlaca(rs.getString("placa"));
				c.setMarca(rs.getString("marca"));
				c.setAno(Integer.parseInt(rs.getString("ano")));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
    }

    @Override
    public void remover(String placa) throws OnibusException {
		try {
			String sql = "DELETE FROM Onibus " + "WHERE placa=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, String.valueOf(placa));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
    

    @Override
    public void atualizar(Onibus on) throws OnibusException {
        try {
			String sql = "UPDATE Onibus SET marca=?, ano=?" + "WHERE placa=?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, on.getMarca());
			stmt.setInt(2, on.getAno());
			stmt.setString(3, String.valueOf(on.getPlaca()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
