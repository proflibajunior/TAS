package br.edu.materdei.tas.desktop.gui.core;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class CPFDocument extends PlainDocument {

    private final int NUMERO_DIGITOS_MAXIMO = 11;

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        String texto = getText(0, getLength());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return;
            }
        }
        
        String novoTexto = texto.replace(".", "").replace("-", "") + str;
        
        if (novoTexto.length() <= this.NUMERO_DIGITOS_MAXIMO) {
            super.remove(0, getLength());
            
            StringBuilder s = new StringBuilder(novoTexto);
            
            if (s.length() >= 3) {
                s.insert(3, ".");
            }   
            if (s.length() >= 7) {
                s.insert(7, ".");
            }   
            if (s.length() >= 11) {
                s.insert(11, "-");
            }
            
            super.insertString(0, s.toString(), a);
        }
    }

    @Override
    public void remove(int offset, int length) throws BadLocationException {
        super.remove(offset, length);
        
        if ((offset != 3)&&(offset != 7)&&(offset != 11)) {
            String texto = getText(0, getLength());
            texto = texto.replace("-", "");
            texto = texto.replace(".", "");
            super.remove(0, getLength());
            insertString(0, texto, null);
        }
    }
}
