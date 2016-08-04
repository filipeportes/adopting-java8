package org.gojava.java8.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return Arrays.stream(Estado.values())
                .filter(e -> e.getRegiao().equals(regiao))
                .collect(Collectors.toList());
    }
}
