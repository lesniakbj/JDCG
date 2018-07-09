package ui.listeners;

import sim.domain.statics.CampaignType;
import sim.domain.statics.ConflictEra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static ui.util.ImageScaleUtil.NORMAL_IMAGE_RATIO;
import static ui.util.ImageScaleUtil.tryLoadImage;

public class EraSelectionListener implements ActionListener {
    private JComboBox<String> comboBox;
    private JLabel descriptionLabel;
    private JPanel imagePanel;
    private JComboBox<String> campaignTypeBox;

    // Selections
    private ConflictEra selectedEra;
    private CampaignType selectedType;

    private static final int MAP_WIDTH = 550;

    public EraSelectionListener(JComboBox comboBox, JLabel descriptionLabel, JPanel imagePanel, JComboBox<String> campaignTypeBox) {
        this.comboBox = comboBox;
        this.descriptionLabel = descriptionLabel;
        this.imagePanel = imagePanel;
        this.campaignTypeBox = campaignTypeBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        selectedEra = ConflictEra.fromName((String)comboBox.getSelectedItem());
        selectedType = CampaignType.fromName((String)campaignTypeBox.getSelectedItem());

        // Set the description
        descriptionLabel.setText("<html><body style='width:600px'>" + selectedEra.getEraDescription() + selectedType.getCharacteristics() + "</html>");

        // Set the image
        BufferedImage mapImage = tryLoadImage("/" + selectedEra.getEraName().replace(" ", "_") + ".jpg");
        Image scaled = mapImage.getScaledInstance(MAP_WIDTH * 2, (int)((MAP_WIDTH * 2) * NORMAL_IMAGE_RATIO), Image.SCALE_SMOOTH);
        imagePanel.removeAll();
        imagePanel.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.CENTER);
    }

    public ConflictEra getSelectedEra() {
        return selectedEra;
    }

    public CampaignType getSelectedType() {
        return selectedType;
    }
}
