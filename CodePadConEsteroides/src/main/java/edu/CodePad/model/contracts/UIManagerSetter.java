package edu.CodePad.model.contracts;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.UIManager;

public interface UIManagerSetter {
    Font JB_BOLDER = new Font("JetBrainsMono Nerd Font Mono", 1, 16);
    Font JB_BOLD = new Font("JetBrainsMono Nerd Font Mono", 1, 14);
    Font JBRAINS = new Font("JetBrainsMono NF", 1, 13);
    
    default void runUIManager() {
        UIManager.put("Table.font", JBRAINS);
        UIManager.put("Label.font", JBRAINS);
        UIManager.put("Button.font", JB_BOLD);
        UIManager.put("TextPane.font", JBRAINS);
        UIManager.put("MenuItem.font", JBRAINS);
        UIManager.put("TableHeader.font", JB_BOLD);
        UIManager.put("ToggleButton.font", JB_BOLD);

        UIManager.put("ScrollBar.thumbDarkShadow", Color.LIGHT_GRAY);
        UIManager.put("ScrollBar.thumbHighlight", Color.DARK_GRAY);
        UIManager.put("ScrollBar.thumbShadow", Color.DARK_GRAY);
        UIManager.put("ScrollBar.background", Color.GRAY);
        UIManager.put("ScrollBar.thumb", Color.DARK_GRAY);
        UIManager.put("ScrollBar.track", Color.GRAY);
        UIManager.put("ScrollBar.width", 12);

        UIManager.put("ComboBox.font", JBRAINS);
        UIManager.put("ComboBox.background", Color.GRAY);
        UIManager.put("ComboBox.foreground", Color.BLACK);
        UIManager.put("ComboBox.buttonBackground", Color.DARK_GRAY);
        UIManager.put("ComboBox.border", BorderFactory.createEmptyBorder());
        UIManager.put("ComboBox.focus", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", Color.BLACK);
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("ComboBox.selectionFocus", Color.WHITE);

        UIManager.put("ScrollPane.background", Color.LIGHT_GRAY);
        UIManager.put("TextPane.background", Color.LIGHT_GRAY);

        UIManager.put("TableHeader.background", Color.DARK_GRAY);
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("Table.background", Color.LIGHT_GRAY);
        UIManager.put("Table.gridColor", Color.DARK_GRAY);

        UIManager.put("Button.background", Color.DARK_GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.select", Color.BLACK);
        UIManager.put("Button.focus", Color.LIGHT_GRAY);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 10, 5, 10));

        UIManager.put("MenuBar.background", Color.DARK_GRAY);
        UIManager.put("MenuBar.opaque", true);

        UIManager.put("Menu.selectionBackground", Color.GRAY);
        UIManager.put("Menu.selectionForeground", Color.WHITE);
        UIManager.put("Menu.background", Color.DARK_GRAY);
        UIManager.put("Menu.foreground", Color.WHITE);
        UIManager.put("Menu.opaque", true);
        UIManager.put("Menu.border", BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 2, 1, Color.GRAY), BorderFactory.createEmptyBorder(3, 5, 3, 5)));

        UIManager.put("MenuItem.selectionBackground", Color.GRAY);
        UIManager.put("MenuItem.selectionForeground", Color.WHITE);
        UIManager.put("MenuItem.background", Color.DARK_GRAY);
        UIManager.put("MenuItem.foreground", Color.WHITE);
        UIManager.put("MenuItem.opaque", true);
        UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder(3, 5, 3, 5));

        UIManager.put("Separator.foreground", Color.WHITE);
        UIManager.put("Separator.shadow", Color.RED);
    }

}
