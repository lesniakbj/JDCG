package ui.listeners;

import sim.domain.statics.MapConstants;
import ui.containers.MapSelectionPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MapSelectionListener implements MouseListener {
    private List<MapSelectionPanel> watchedPanels;
    private MapSelectionPanel selectedPanel;

    @Override
    public void mouseClicked(MouseEvent e) {
        MapSelectionPanel clickedPanel = (MapSelectionPanel) e.getSource();
        if(clickedPanel == selectedPanel) {
            return;
        }

        selectedPanel = clickedPanel;

        // Show the selected panel by applying an outline
        Border padding = BorderFactory.createEmptyBorder(9, 9, 9, 9);
        Border outline = BorderFactory.createLineBorder(Color.RED, 1);
        selectedPanel.setBorder(BorderFactory.createCompoundBorder(padding, outline));

        // Remove the outlines from any other panels
        Border normalPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        watchedPanels.stream().filter((panel) -> panel != selectedPanel).forEach((panel) -> panel.setBorder(normalPadding));
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public void addWatchedPanel(JPanel panel) {
        if(watchedPanels == null) {
            watchedPanels = new ArrayList<>();
        }
        watchedPanels.add((MapSelectionPanel)panel);
    }

    public MapConstants getSelectedMap() {
        if(selectedPanel != null) {
            return selectedPanel.getMap();
        }
        return MapConstants.CAUCASUS;
    }
}
