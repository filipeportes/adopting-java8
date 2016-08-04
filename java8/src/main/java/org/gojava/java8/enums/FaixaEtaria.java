package org.gojava.java8.enums;

import java.util.Arrays;

public enum FaixaEtaria {
    INFANTIL(0, 9),
    DEZ(10, 19),
    VINTE(20, 29),
    TRINTA(30, 39),
    QUARENTA(40, 49),
    HORA_EXTRA(50, -1);

    private int inicio;
    private int fim;

    FaixaEtaria(int inicio, int fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public String getDescricao() {
        if (fim > 0) {
            return "de " + this.inicio + " até  " + this.fim;
        } else {
            return "a partir de " + this.inicio;
        }
    }

    public static FaixaEtaria getFaixaEtariaPorIdade(int idade) {
        return Arrays.stream(FaixaEtaria.values())
                .filter(f -> idade >= f.inicio && (idade <= f.fim || f.fim < 0))
                .findFirst().orElse(FaixaEtaria.INFANTIL);
    }
}

