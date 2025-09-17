package com.back.dao;

import com.back.model.RepairOrder;
import com.back.util.Connectate;
import com.back.view.ValidationMessages;

import java.sql.Connection;
import java.sql.SQLException;

public class RepairOrderDAO {

    public boolean createRepairOrder(RepairOrder repairOrder){
        String query = "INSERT INTO repairOrder (idMachine, idTechnician, dateSolicitation, status) VALUES (?,?,?,?)";

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setInt(1, repairOrder.getIdMachine());
            stmt.setInt(2, repairOrder.getIdTechnician());
            stmt.setDate(3, repairOrder.getDateSolicitation());
            stmt.setString(4, String.valueOf(repairOrder.getStatus()));
            stmt.executeUpdate();

            return true;

        }catch(SQLException e){
            ValidationMessages.errorConnecting();
            e.printStackTrace();
            return false;
        }
    }

}
