package org.gojava.java8.controller;

import lombok.Getter;
import org.gojava.java8.enums.Estado;
import org.gojava.java8.enums.FaixaEtaria;
import org.gojava.java8.enums.Regiao;
import org.gojava.java8.enums.Sexo;
import org.gojava.java8.model.Cliente;
import org.gojava.java8.repository.ClienteRepository;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Named
@ViewScoped
public class ClienteController implements Serializable {

    @Inject
    private ClienteRepository clienteRepository;

    @Getter
    private PieChartModel graficoPorSexo;
    @Getter
    private PieChartModel graficoPorRegiao;
    @Getter
    private PieChartModel graficoPorFaixaEtaria;
    @Getter
    private BarChartModel graficoPorEstadoESexo;

    @Getter
    private List<Cliente> clientes = new ArrayList<>();

    @PostConstruct
    public void init() {

        clientes = clienteRepository.findAll();

        carregarGraficoPorSexo();
        carregarGraficoPorRegiao();
        carregarGraficoPorFaixaEtaria();
        carregarGraficoPorEstadoESexo();
    }

    private void carregarGraficoPorSexo() {
        graficoPorSexo = new PieChartModel();
        graficoPorSexo.setTitle("Clientes por Sexo");
        graficoPorSexo.setLegendPosition("w");

        Map<Sexo, Long> mapClientesPorSexo = clientes.stream()
                .collect(groupingBy(Cliente::getSexo, counting()));

        mapClientesPorSexo.keySet()
                .forEach(sexo -> this.graficoPorSexo.set(sexo.getDescricao(),
                        mapClientesPorSexo.get(sexo)));
    }

    private void carregarGraficoPorRegiao() {
        graficoPorRegiao = new PieChartModel();
        graficoPorRegiao.setTitle("Clientes por Região");
        graficoPorRegiao.setLegendPosition("w");

        Map<Regiao, Long> mapClientesPorRegiao = clientes.stream()
                .collect(groupingBy(c -> c.getEstado().getRegiao(), counting()));

        mapClientesPorRegiao.keySet().stream()
                .sorted((r1, r2) -> r1.getNome().compareTo(r2.getNome()))
                .forEach(regiao -> this.graficoPorRegiao.set(regiao.getNome(),
                        mapClientesPorRegiao.get(regiao)));
    }

    private void carregarGraficoPorFaixaEtaria() {
        graficoPorFaixaEtaria = new PieChartModel();
        graficoPorFaixaEtaria.setTitle("Clientes por Faixa Etária");
        graficoPorFaixaEtaria.setLegendPosition("w");

        Map<FaixaEtaria, Long> mapClientesPorFaixaEtaria = clientes.stream()
                .collect(Collectors.groupingBy(Cliente::getFaixaEtaria, counting()));

        mapClientesPorFaixaEtaria.keySet().stream().sorted()
                .forEach(faixa -> this.graficoPorFaixaEtaria.set(faixa.getDescricao(),
                        mapClientesPorFaixaEtaria.get(faixa)));
    }

    private void carregarGraficoPorEstadoESexo() {
        graficoPorEstadoESexo = new BarChartModel();
        graficoPorEstadoESexo.setTitle("Clientes por Estado e Sexo");
        graficoPorEstadoESexo.setLegendPosition("nw");


        final Map<Sexo, Map<Estado, Long>> mapClientesPorEstadoESexo = clientes.stream()
                .collect(groupingBy(Cliente::getSexo, groupingBy(Cliente::getEstado, counting())));


        mapClientesPorEstadoESexo.keySet().forEach(sexo -> {
            ChartSeries serie = new ChartSeries();
            serie.setLabel(sexo.getDescricao());

            final Map<Estado, Long> mapEstado = mapClientesPorEstadoESexo.get(sexo);
            mapEstado.keySet().stream().sorted()
                    .forEach(estado -> serie.set(estado.getSigla(), mapEstado.get(estado)));

            graficoPorEstadoESexo.addSeries(serie);
        });
    }
}
