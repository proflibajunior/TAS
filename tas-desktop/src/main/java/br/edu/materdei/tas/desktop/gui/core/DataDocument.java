package br.edu.materdei.tas.desktop.gui.core;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class DataDocument extends PlainDocument {

    private final int NUMERO_DIGITOS_MAXIMO = 8;

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        String texto = getText(0, getLength());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return;
            }
        }
        
        String novoTexto = texto.replace("/", "") + str;
        
        if (novoTexto.length() <= this.NUMERO_DIGITOS_MAXIMO) {
            super.remove(0, getLength());
            
            StringBuilder s = new StringBuilder(novoTexto);
            
            if (s.length() >= 2) {
                s.insert(2, "/");
            }   
            if (s.length() >= 5) {
                s.insert(5, "/");
            }   
            
            super.insertString(0, s.toString(), a);
        }
    }

    @Override
    public void remove(int offset, int length) throws BadLocationException {
        super.remove(offset, length);
        
        if ((offset != 2)&&(offset != 5)) {
            String texto = getText(0, getLength());
            texto = texto.replace("/", "");
            super.remove(0, getLength());
            insertString(0, texto, null);
        }
    }
}
