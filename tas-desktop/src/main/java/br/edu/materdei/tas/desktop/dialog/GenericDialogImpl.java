package br.edu.materdei.tas.desktop.dialog;

import br.edu.materdei.tas.desktop.MainFrame;
import br.edu.materdei.tas.desktop.exception.NotValidateException;
import br.edu.materdei.tas.desktop.interfaces.IGenericDialog;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JDialog;


public class GenericDialogImpl extends JDialog implements IGenericDialog {
    public boolean fail = false;
    protected ArrayList<String> fieldsEmpty;
    protected MainFrame mainframe;

    public GenericDialogImpl(Frame parent, boolean modal) {
        super(parent, modal);
    }

    @Override
    public void validarDados() throws NotValidateException {}

    @Override
    public void salvar() {}
}
