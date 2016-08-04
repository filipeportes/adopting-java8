package org.gojava.java7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.gojava.java7.enums.Estado;
import org.gojava.java7.enums.FaixaEtaria;
import org.gojava.java7.enums.Sexo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
@AllArgsConstructor
public class Cliente {

    private String nome;

    private Estado estado;

    private Sexo sexo;

    private Date dataNascimento;

    private int getIdade() {
        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(dataNascimento);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        dateOfBirth.add(Calendar.YEAR, age);
        if (today.before(dateOfBirth)) {
            age--;
        }

        return age;
    }

    public FaixaEtaria getFaixaEtaria() {
        return FaixaEtaria.getFaixaEtariaPorIdade(getIdade());
    }
}
