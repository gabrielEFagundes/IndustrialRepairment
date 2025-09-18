package com.back.dao;

import com.back.model.Part;
import com.back.util.Connectate;
import com.back.view.ValidationMessages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartDAO {

    // don't forget to also add a duplicate thingy in here
    public boolean signPart(Part part){
        String query = "INSERT INTO part (name, storage) VALUES (?,?)";

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setString(1, part.getName());
            stmt.setDouble(2, part.getStorage());
            stmt.executeUpdate();

            return true;

        }catch(SQLException e){
            ValidationMessages.errorConnecting();
            e.printStackTrace();
            return false;
        }
    }

    public List<Part> allParts(){
        String query = "SELECT id, name, storage FROM part";
        List<Part> parts = new ArrayList<>();

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double storage = rs.getDouble("storage");
                parts.add(new Part(id, name, storage));
            }

        }catch(SQLException e){
            ValidationMessages.errorConnecting();
            e.printStackTrace();
        }
        return parts;
    }

    public boolean updatePartStorage(double amount, int partId){
        String query = """
                UPDATE part
                SET storage = storage - ?
                WHERE id = ?
                """;

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setDouble(1, amount);
            stmt.setInt(2, partId);
            stmt.executeUpdate();

            return true;

        }catch(SQLException e){
            ValidationMessages.errorConnecting();
            e.printStackTrace();
            return false;
        }
    }

}
