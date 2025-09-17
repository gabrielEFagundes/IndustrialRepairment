package com.back.dao;

import com.back.model.Part;
import com.back.util.Connectate;
import com.back.view.ValidationMessages;

import java.sql.Connection;
import java.sql.SQLException;

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

}
