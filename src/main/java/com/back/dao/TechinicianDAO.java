package com.back.dao;

import com.back.model.Technician;
import com.back.util.Connectate;
import com.back.view.ValidationMessages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TechinicianDAO {

    // also need to add the verification of duplicates!!
    public boolean signTechnician(Technician technician){
        String query = "INSERT INTO technician (name, specialty) VALUES (?,?)";

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setString(1, technician.getName());
            stmt.setString(2, technician.getSpecialty());
            stmt.executeUpdate();

            return true;

        }catch(SQLException e){
            ValidationMessages.errorConnecting();
            e.printStackTrace();
            return false;
        }
    }

    public List<Technician> allTechnicians(){
        String query = "SELECT id, name, specialty FROM technician";
        List<Technician> technicians = new ArrayList<>();

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String specialty = rs.getString("specialty");
                technicians.add(new Technician(id, name, specialty));
            }

        }catch (SQLException e){
            ValidationMessages.errorConnecting();
            e.printStackTrace();
        }
        return technicians;
    }

}
