package org.gojava.java8.repository;

import org.gojava.java8.enums.Estado;
import org.gojava.java8.enums.Sexo;
import org.gojava.java8.model.Cliente;

import javax.ejb.Singleton;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Singleton
public class ClienteRepository {

    private List<Cliente> clientes;

    public List<Cliente> findAll() {

        if(clientes == null) {

            clientes = new ArrayList<>();

            for (int i = 0; i <= 10000; i++) {
                Random rd = new Random();
                Estado estado = Estado.values()[rd.nextInt(27)];
                LocalDate data = LocalDate.of(1966 + rd.nextInt(50), rd.nextInt(11) + 1, rd.nextInt(28) + 1);

                Sexo sexo;
                if(i % 10 == 0) {
                    sexo = Sexo.FEMININO;
                } else {
                     sexo = Sexo.values()[rd.nextInt(2)];
                }

                clientes.add(new Cliente("inscrito " + i, estado, sexo, data));
            }
        }

        return clientes;
    }
}
