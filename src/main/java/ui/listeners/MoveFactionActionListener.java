package ui.listeners;

import sim.domain.statics.Faction;
import sim.domain.statics.FactionSide;
import ui.containers.NewCampaignPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MoveFactionActionListener implements ActionListener {
    private JTable neutralFactionTable;
    private Map<FactionSide, JTable> factionTables;

    @Override
    public void actionPerformed(ActionEvent e) {
        // Parse the direction of the movement
        NewCampaignPanel.FactionMove move = NewCampaignPanel.FactionMove.fromName(e.getActionCommand());

        // Check to see if the selection is coming from the Neutral Table
        int selectedRow =  neutralFactionTable.getSelectedRow();
        if(selectedRow != -1) {
            // Parse the row before we move it
            String selectedNeutralFaction = (String) neutralFactionTable.getValueAt(selectedRow, 0);
            Faction parsedFaction = Faction.fromName(selectedNeutralFaction);

            // Move the row
            DefaultTableModel model = (DefaultTableModel) neutralFactionTable.getModel();
            model.removeRow(selectedRow);
            String[] data = {parsedFaction.getDcsFactionName(), parsedFaction.getOverallStrength().getFactionStrength()};
            switch (move) {
                case LEFT:
                    ((DefaultTableModel) factionTables.get(FactionSide.BLUFOR).getModel()).addRow(data);
                    break;
                case RIGHT:
                    ((DefaultTableModel) factionTables.get(FactionSide.REDFOR).getModel()).addRow(data);
                    break;
                case RETURN:
                default:
                    break;
            }
        }

        // Otherwise check to see if it is one of the periphery tables
        JTable blueforSide = factionTables.get(FactionSide.BLUFOR);
        selectedRow =  blueforSide.getSelectedRow();
        if(selectedRow != -1) {
            // Parse the row before we move it
            String selectedFaction = (String) blueforSide.getValueAt(selectedRow, 0);
            Faction parsedFaction = Faction.fromName(selectedFaction);

            // Move the row
            DefaultTableModel model = (DefaultTableModel) blueforSide.getModel();
            model.removeRow(selectedRow);
            String[] data = {parsedFaction.getDcsFactionName(), parsedFaction.getOverallStrength().getFactionStrength()};
            switch (move) {
                case RIGHT:
                    ((DefaultTableModel) factionTables.get(FactionSide.REDFOR).getModel()).addRow(data);
                    break;
                case RETURN:
                    ((DefaultTableModel) neutralFactionTable.getModel()).addRow(data);
                    break;
                case LEFT:
                default:
                    break;
            }
        }

        // Otherwise check to see if it is one of the periphery tables
        JTable redforSide = factionTables.get(FactionSide.REDFOR);
        selectedRow =  redforSide.getSelectedRow();
        if(selectedRow != -1) {
            // Parse the row before we move it
            String selectedFaction = (String) redforSide.getValueAt(selectedRow, 0);
            Faction parsedFaction = Faction.fromName(selectedFaction);

            // Move the row
            DefaultTableModel model = (DefaultTableModel) redforSide.getModel();
            model.removeRow(selectedRow);
            String[] data = {parsedFaction.getDcsFactionName(), parsedFaction.getOverallStrength().getFactionStrength()};
            switch (move) {
                case LEFT:
                    ((DefaultTableModel) factionTables.get(FactionSide.BLUFOR).getModel()).addRow(data);
                    break;
                case RETURN:
                    ((DefaultTableModel) neutralFactionTable.getModel()).addRow(data);
                    break;
                case RIGHT:
                default:
                    break;
            }
        }
    }

    public void setNeutralFactionTable(JTable neutralFactionTable) {
        this.neutralFactionTable = neutralFactionTable;
    }

    public void setFactionTable(JTable factionTable, FactionSide side) {
        if(factionTables == null) {
            factionTables = new HashMap<>();
        }
        factionTables.putIfAbsent(side, factionTable);
    }

    public JTable getFactionTable(FactionSide side) {
        if(side == FactionSide.NEUTRAL) {
            return neutralFactionTable;
        }

        return factionTables.get(side);
    }
}
