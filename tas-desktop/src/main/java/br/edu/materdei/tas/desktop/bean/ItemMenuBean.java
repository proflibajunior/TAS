package br.edu.materdei.tas.desktop.bean;

import javax.swing.JPanel;

public class ItemMenuBean {
    private String nomeAba;
    private JPanel view;

    
    public ItemMenuBean() {
    }

    public ItemMenuBean(String nomeAba, JPanel view) {
        this.nomeAba = nomeAba;
        this.view = view;
    }
    

    public String getNomeAba() {
        return nomeAba;
    }

    public void setNomeAba(String nomeAba) {
        this.nomeAba = nomeAba;
    }
    
    public JPanel getView() {
        return view;
    }

    public void setView(JPanel view) {
        this.view = view;
    }
    
}
