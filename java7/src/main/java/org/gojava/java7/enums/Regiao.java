package org.gojava.java7.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public enum Regiao {
    
    CENTROOESTE("Centro-Oeste") {
        @Override
        public List<Estado> getEstados() {
            return estadosPorRegiao(CENTROOESTE);
        }
    },
    NORDESTE("Nordeste") {
        @Override
        public List<Estado> getEstados() {
            return estadosPorRegiao(NORDESTE);
        }
    },
    NORTE("Norte") {
        @Override
        public List<Estado> getEstados() {
            return estadosPorRegiao(NORTE);
        }
    },
    SUDESTE("Sudeste") {
        @Override
        public List<Estado> getEstados() {
            return estadosPorRegiao(SUDESTE);
        }
    },
    SUL("Sul") {
        @Override
        public List<Estado> getEstados() {
            return estadosPorRegiao(SUL);
        }
    };

    @Getter
    private final String nome;
    protected abstract List<Estado> getEstados();

    Regiao(String nome) {
        this.nome = nome;
    }

    protected List<Estado> estadosPorRegiao(Regiao regiao) {
        List<Estado> lista = new ArrayList<>();

        for (Estado value : Estado.values()) {
            if (value.getRegiao().equals(regiao)) {
                lista.add(value);
            }
        }
        return lista;
    }
}
