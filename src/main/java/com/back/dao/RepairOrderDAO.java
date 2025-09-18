package com.back.dao;

import com.back.dto.RepairOrderDTO;
import com.back.model.RepairOrder;
import com.back.util.Connectate;
import com.back.view.ValidationMessages;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<RepairOrderDTO> pendentRepairOrders(){
        String query = """
                SELECT repairOrder.id,
                machine.name AS MachineName,
                technician.name AS TechnicianName,
                repairOrder.dateSolicitation,
                repairOrder.status
                FROM repairOrder
                JOIN machine ON repairOrder.idMachine = machine.id
                JOIN technician ON repairOrder.idTechnician = technician.id
                WHERE repairOrder.status = 'PENDENT'
                """;
        List<RepairOrderDTO> repairOrders = new ArrayList<>();

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String machName = rs.getString("MachineName");
                String techName = rs.getString("TechnicianName");
                Date dateSol =  rs.getDate("dateSolicitation");
                String stats = rs.getString("status");
                repairOrders.add(new RepairOrderDTO(id, machName, techName, dateSol, stats));
            }

        }catch(SQLException e){
            ValidationMessages.errorConnecting();
            e.printStackTrace();
        }
        return repairOrders;
    }

    public boolean updateStatus(int id){
        String query = """
                UPDATE repairOrder
                SET status = 'EXECUTED'
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
