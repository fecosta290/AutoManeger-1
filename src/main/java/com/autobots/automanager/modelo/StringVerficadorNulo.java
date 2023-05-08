package com.autobots.automanager.modelo;

public class StringVerficadorNulo{

    public Boolean verificarNulo(String dado){
        Boolean nulo = true;
        if(!(dado == null)){
            if(!dado.isBlank()){
                nulo = false;
            }
        }
        return nulo;
    }

}