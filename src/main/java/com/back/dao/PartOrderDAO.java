package com.back.dao;

import com.back.dto.PartOrderDTO;
import com.back.model.PartOrder;
import com.back.util.Connectate;
import com.back.view.ValidationMessages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class PartOrderDAO {

    public boolean signPartOrders(List<PartOrder> partOrders, PartOrder partOrder){
        String query = "INSERT INTO partOrder (idOrder, idPart, amount) VALUES (?,?,?)";
        int size = partOrders.size();

        for(PartOrder po : partOrders){
            try(Connection conn = Connectate.begin();
                var stmt = conn.prepareStatement(query)){

                stmt.setInt(1, po.getIdOrder());
                stmt.setInt(2, po.getIdPart());
                stmt.setDouble(3, po.getAmount());
                stmt.executeUpdate();

                size--;

                if(size == 0){
                    return true;
                }

            }catch(SQLException e){
                ValidationMessages.errorConnecting();
                e.printStackTrace();

            }
        }
        return false;
    }

    public List<PartOrderDTO> pendentAndStorageParts(){
        String query = """
                SELECT repairOrder.id AS repID,
                part.id AS partID,
                part.name,
                part.storage,
                repairOrder.status,
                partOrder.amount
                FROM partOrder
                JOIN repairOrder ON partOrder.idOrder = repairOrder.id
                JOIN part ON partOrder.idPart = part.id
                WHERE repairOrder.status = 'PENDENT'
                """;
        List<PartOrderDTO> parts = new ArrayList<>();

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int repId = rs.getInt("repID");
                int partId = rs.getInt("partID");
                String name = rs.getString("name");
                double storage = rs.getDouble("storage");
                String status = rs.getString("status");
                double amount = rs.getDouble("amount");

                parts.add(new PartOrderDTO(repId, partId, name, storage, status, amount));
            }

        }catch(SQLException e){
            ValidationMessages.errorConnecting();
            e.printStackTrace();
        }
        return parts;
    }

}
