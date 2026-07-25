package model.dao;

import java.sql.*;
import java.util.*;
import model.Usuario;
import model.Usuario;
import model.dao.ConexaoDB;

public class UsuarioDAO {
    
    private Connection con;
    
    public UsuarioDAO() throws SQLException{
        con = ConexaoDB.getConnection();
    }
    
    public int InserirUsuario(Usuario usuario){
        try{
            String sql = "insert into usuarios (nome, sobrenome, email, cidade) values (?,?,?,?);";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getSobrenome());
            pst.setString(3, usuario.getEmail());
            pst.setString(4, usuario.getCidade());
            pst.executeUpdate();
           
            return 1;
        }catch(SQLException e){
            e.printStackTrace(); // vai aparecer o erro no console do Netbeans
            throw new RuntimeException(e.getMessage()); // vai aparecer o erro em um popup
        }
    }
    
    public List<Usuario> getUsuarios() throws SQLException{
        List<Usuario> usuarios = new  ArrayList<>();
        try{
            String sql = "select * from usuarios";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String email = rs.getString("email");
                String cidade = rs.getString("cidade");
                usuarios.add(new Usuario(id, nome, sobrenome, email, cidade));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }
    
    //UPDATE
    
    public int atualizarUsuario(Usuario usuario){
        try{
            String sql = "update usuarios set nome = ?, sobrenome = ?, email = ?, cidade = ? where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getSobrenome());
            pst.setString(3, usuario.getEmail());
            pst.setString(4, usuario.getCidade());
            pst.setInt(5, usuario.getId());
            pst.executeUpdate();
            return 1;
        }catch(SQLException e){
            return 0;
        }
    }
    
    //DELETE
    
    public void deleteUsuario(int id){
        try{
            String sql = "delete from usuarios where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}