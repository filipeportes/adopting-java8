package org.gojava.java7.repository;

import org.gojava.java7.enums.Estado;
import org.gojava.java7.enums.Sexo;
import org.gojava.java7.model.Cliente;

import javax.ejb.Singleton;
import java.util.*;

@Singleton
public class ClienteRepository {

    private List<Cliente> inscricoes;

    public List<Cliente> findAll() {

        if(inscricoes == null) {

            inscricoes = new ArrayList<>();

            for (int i = 0; i <= 10000; i++) {
                Random rd = new Random();
                Estado estado = Estado.values()[rd.nextInt(27)];
                Date data = new GregorianCalendar(1966 + rd.nextInt(50), rd.nextInt(12), rd.nextInt(28) + 1).getTime();

                Sexo sexo;
                if(i % 10 == 0) {
                    sexo = Sexo.FEMININO;
                } else {
                     sexo = Sexo.values()[rd.nextInt(2)];
                }

                inscricoes.add(new Cliente("inscrito " + i, estado, sexo, data));
            }
        }

        return inscricoes;
    }
}
