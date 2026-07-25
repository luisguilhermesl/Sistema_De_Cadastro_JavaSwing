
package Utils;


import model.dao.ConexaoDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BancoUtils {
    
    public static void criarTabelaUsuarios() throws SQLException {
        Connection conexao = ConexaoDB.getConnection();
        if (conexao != null) {
            String sql = "create table if not exists usuarios ("
                    + "id int auto_increment not null primary key,"
                    + "nome varchar(100) not null,"
                    + "sobrenome varchar(100) not null,"
                    + "email varchar(100) not null unique,"
                    + "cidade varchar(100) not null"
                    + ")";
            
            try(PreparedStatement pst = conexao.prepareCall(sql)) {
                pst.execute();
                System.out.println("Table 'usuarios' criada com sucesso!");
            }
            catch(SQLException e) {
                System.out.println("Erro ao criar tabela 'usuario': " +e.getMessage());
            }
            finally {
                try {
                    conexao.close();
                }
                catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " +e.getMessage());
                }
            }
        }
    }
}