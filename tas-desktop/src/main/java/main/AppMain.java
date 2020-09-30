package main;

import br.edu.materdei.tas.desktop.MainFrame;


public class AppMain {
    public static void main(String[] args) {                
        MainFrame frmMain = new MainFrame();
        frmMain.dispose();
        frmMain.setUndecorated(true);
        frmMain.setVisible(true);
    }
}
