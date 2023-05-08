package com.autobots.automanager.modelo;

import com.autobots.automanager.entidades.Documento;

public class DocumentoAtualizador {
    
    private StringVerficadorNulo verificardor = new StringVerficadorNulo();

    public void atualizador(Documento documento, Documento atualizado){
        if(atualizado != null){
            if(!verificardor.verificarNulo(atualizado.getTipo())){
                documento.setTipo(atualizado.getTipo());
            }
            if(!verificardor.verificarNulo(atualizado.getNumero())){
                documento.setNumero(atualizado.getNumero());
            }
        }
    }

    

}
