package com.scc.dao;

import com.scc.model.ScenarioEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    public void addRecord(ScenarioEntity entity) {
        String sql = "INSERT INTO coffee_orders (customer_name, drink_item, price, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entity.getCustomerName());
            pstmt.setString(2, entity.getDrinkItem());
            pstmt.setDouble(3, entity.getPrice());
            pstmt.setString(4, entity.getStatus());

            pstmt.executeUpdate();
            System.out.println("\nSUCCESS: Order added to queue!");
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

    public List<ScenarioEntity> getAllRecords() {
        List<ScenarioEntity> records = new ArrayList<>();
        String sql = "SELECT id, customer_name, drink_item, price, status FROM coffee_orders";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ScenarioEntity entity = new ScenarioEntity(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getString("drink_item"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );
                records.add(entity);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching records: " + e.getMessage());
        }
        return records;
    }
}