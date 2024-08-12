package LABBD.crudviagem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDAOImpl extends DBconnection implements MotoristaDAO {
    public MotoristaDAOImpl(){
        super();
    }

    @Override
    public void adicionar(Motorista mt) throws MotoristaException {
        String sql = "INSERT INTO Motorista(codigo, nome, nacionalidade) VALUES (?, ?, ?)";

            PreparedStatement pstm;
            try {
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, mt.getCodigo());
                pstm.setString(2, mt.getNome());
                pstm.setString(3, mt.getNaturalidade());
                pstm.executeUpdate();
            }  catch (SQLException e) {
                e.printStackTrace();
            }
        
    }

    @Override
    public List<Motorista> pesquisarTodos() throws MotoristaException {
        List<Motorista> lista = new ArrayList<>();

		try {
			String sql = "SELECT * FROM Motorista ";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Select executado com sucesso");

			while (rs.next()) {
				Motorista c = new Motorista();
				c.setCodigo(Integer.parseInt(rs.getString("codigo")));
				c.setNome(rs.getString("nome"));
				c.setNaturalidade(rs.getString("naturalidade"));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
    }

    @Override
    public List<Motorista> pesquisarPorNome(String nome) throws MotoristaException {
        List<Motorista> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Motorista " + "WHERE nome LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			System.out.println("Select por NomeAutor executado com sucesso");

			while (rs.next()) {
				Motorista c = new Motorista();
				c.setCodigo(Integer.parseInt(rs.getString("codigo")));
				c.setNome(rs.getString("nome"));
				c.setNaturalidade(rs.getString("naturalidade"));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
    }

    @Override
    public void remover(int codigo) throws MotoristaException {
		try {
			String sql = "DELETE FROM Motorista " + "WHERE codigo=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, String.valueOf(codigo));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
    

    @Override
    public void atualizar(Motorista mt) throws MotoristaException {
        try {
			String sql = "UPDATE Motorista SET nome=?, naturalidade=?" + "WHERE id=?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mt.getNome());
			stmt.setString(2, mt.getNaturalidade());
			stmt.setString(3, String.valueOf(mt.getCodigo()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

