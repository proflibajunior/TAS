package br.edu.materdei.tas.desktop.interfaces;

import br.edu.materdei.tas.desktop.exception.NotValidateException;


public interface IGenericDialog {
    void validarDados() throws NotValidateException;
    void salvar();
}
