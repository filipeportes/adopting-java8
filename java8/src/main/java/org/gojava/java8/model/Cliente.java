package org.gojava.java8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.gojava.java8.enums.Estado;
import org.gojava.java8.enums.FaixaEtaria;
import org.gojava.java8.enums.Sexo;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
public class Cliente {

    private String nome;

    private Estado estado;

    private Sexo sexo;

    private LocalDate dataNascimento;

    private int getIdade() {
        Period period = Period.between(dataNascimento, LocalDate.now());
        return period.getYears();
    }

    public FaixaEtaria getFaixaEtaria() {
        return FaixaEtaria.getFaixaEtariaPorIdade(getIdade());
    }
}
