package com.back.dao;

import com.back.model.Machine;
import com.back.util.Connectate;
import com.back.view.ValidationMessages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineDAO {

    public boolean verifyDuplicate(Machine machine) throws SQLException{
        String query = "SELECT COUNT(0) AS line FROM machine WHERE name = ? OR section = ?";

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setString(1, machine.getName());
            stmt.setString(2, machine.getSection());

            ResultSet rs = stmt.executeQuery();

            if(rs.next() && rs.getInt("line") > 0){
                return true;
            }
        }
        return false;
    }

    public boolean signMachine(Machine machine) {
        String query = "INSERT INTO machine (name, section, status) VALUES (?,?,?)";

        try (Connection conn = Connectate.begin();
             var stmt = conn.prepareStatement(query)) {

            stmt.setString(1, machine.getName());
            stmt.setString(2, machine.getSection());
            stmt.setString(3, String.valueOf(machine.getStatus()));

            if(!verifyDuplicate(machine)){
                stmt.executeUpdate();
                return true;
            }else{
                ValidationMessages.duplicateElement();
                return false;
            }

        }catch (SQLException e){
            ValidationMessages.errorConnecting();
            e.printStackTrace();
            return false;
        }
    }

    public List<Machine> operatingMachines(){
        String query = """
                SELECT id, name, section
                FROM machine
                WHERE machine.status = 'OPERATIONAL'
                """;
        List<Machine> machines = new ArrayList<>();

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String section = rs.getString("section");
                machines.add(new Machine(id, name, section));
            }

        }catch(SQLException e){
            ValidationMessages.errorConnecting();
            e.printStackTrace();
        }
        return machines;
    }

    public boolean updateStatus(int id){
        String query = """
                UPDATE machine
                SET status = 'OPERATIONAL'
                WHERE id = ?
                """;

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();

            return true;

        }catch(SQLException e){
            ValidationMessages.errorConnecting();
            e.printStackTrace();
            return false;
        }
    }

}
