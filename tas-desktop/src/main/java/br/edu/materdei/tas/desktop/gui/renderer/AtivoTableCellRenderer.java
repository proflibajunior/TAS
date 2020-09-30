package br.edu.materdei.tas.desktop.gui.renderer;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class AtivoTableCellRenderer extends DefaultTableCellRenderer {
    JLabel lbl = new JLabel();
    ImageIcon iconFound = new ImageIcon(getClass().getResource("/assets/system/icons/icon-ativo.png"));
    ImageIcon iconNotFound = new ImageIcon(getClass().getResource("/assets/system/icons/icon-inativo.png"));

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        lbl.setText("");
        if ((Boolean) value)
            lbl.setIcon(iconFound);
        else
            lbl.setIcon(iconNotFound);
        return lbl;
    }
}
