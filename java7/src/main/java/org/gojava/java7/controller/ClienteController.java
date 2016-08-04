package org.gojava.java7.controller;

import lombok.Getter;
import org.gojava.java7.enums.Estado;
import org.gojava.java7.enums.FaixaEtaria;
import org.gojava.java7.enums.Regiao;
import org.gojava.java7.enums.Sexo;
import org.gojava.java7.model.Cliente;
import org.gojava.java7.repository.ClienteRepository;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

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
    private Map<Sexo, List<Cliente>> mapClientesPorSexo;

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

        mapClientesPorSexo = new HashMap<>();
        for (Cliente cliente : clientes) {
            if (!mapClientesPorSexo.containsKey(cliente.getSexo())) {
                mapClientesPorSexo.put(cliente.getSexo(), new ArrayList<Cliente>());
            }
            mapClientesPorSexo.get(cliente.getSexo()).add(cliente);
        }

        for (Sexo sexo : mapClientesPorSexo.keySet()) {
            this.graficoPorSexo.set(sexo.getDescricao(), mapClientesPorSexo.get(sexo).size());
        }
    }

    private void carregarGraficoPorRegiao() {
        graficoPorRegiao = new PieChartModel();
        graficoPorRegiao.setTitle("Clientes por Região");
        graficoPorRegiao.setLegendPosition("w");

        Map<Regiao, List<Cliente>> mapClientesPorRegiao = new HashMap<>();
        for (Cliente cliente : clientes) {
            if (!mapClientesPorRegiao.containsKey(cliente.getEstado().getRegiao())) {
                mapClientesPorRegiao.put(cliente.getEstado().getRegiao(), new ArrayList<Cliente>());
            }
            mapClientesPorRegiao.get(cliente.getEstado().getRegiao()).add(cliente);
        }

        List<Regiao> regioes = new ArrayList<>(mapClientesPorRegiao.keySet());
        Collections.sort(regioes, new Comparator<Regiao>() {
            @Override
            public int compare(Regiao o1, Regiao o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        });

        for (Regiao regiao : regioes) {
            this.graficoPorRegiao.set(regiao.getNome(), mapClientesPorRegiao.get(regiao).size());
        }
    }

    private void carregarGraficoPorFaixaEtaria() {
        graficoPorFaixaEtaria = new PieChartModel();
        graficoPorFaixaEtaria.setTitle("Clientes por Faixa Etária");
        graficoPorFaixaEtaria.setLegendPosition("w");

        Map<FaixaEtaria, List<Cliente>> mapClientesPorFaixaEtaria = new HashMap<>();
        for (Cliente cliente : clientes) {
            final FaixaEtaria faixaEtaria = cliente.getFaixaEtaria();
            if (!mapClientesPorFaixaEtaria.containsKey(faixaEtaria)) {
                mapClientesPorFaixaEtaria.put(faixaEtaria, new ArrayList<Cliente>());
            }
            mapClientesPorFaixaEtaria.get(faixaEtaria).add(cliente);
        }

        List<FaixaEtaria> faixaEtarias = new ArrayList<>(mapClientesPorFaixaEtaria.keySet());
        Collections.sort(faixaEtarias);
        for (FaixaEtaria faixa : faixaEtarias) {
            this.graficoPorFaixaEtaria.set(faixa.getDescricao(), mapClientesPorFaixaEtaria.get(faixa).size());
        }
    }

    private void carregarGraficoPorEstadoESexo() {
        graficoPorEstadoESexo = new BarChartModel();
        graficoPorEstadoESexo.setTitle("Clientes por Estado e Sexo");
        graficoPorEstadoESexo.setLegendPosition("nw");

        Map<Sexo, Map<Estado, List<Cliente>>> mapClientesPorEstadoESexo = new HashMap<>();
        for (Sexo sexo : mapClientesPorSexo.keySet()) {
            Map<Estado, List<Cliente>> mapInscricoesPorEstado = new HashMap<>();
            for (Cliente cliente : mapClientesPorSexo.get(sexo)) {
                if (!mapInscricoesPorEstado.containsKey(cliente.getEstado())) {
                    mapInscricoesPorEstado.put(cliente.getEstado(), new ArrayList<Cliente>());
                }
                mapInscricoesPorEstado.get(cliente.getEstado()).add(cliente);
            }
            mapClientesPorEstadoESexo.put(sexo, mapInscricoesPorEstado);
        }

        for (Sexo sexo : mapClientesPorEstadoESexo.keySet()) {
            ChartSeries serie = new ChartSeries();
            serie.setLabel(sexo.getDescricao());

            Map<Estado, List<Cliente>> mapEstado = mapClientesPorEstadoESexo.get(sexo);

            List<Estado> estados = new ArrayList<>(mapEstado.keySet());
            Collections.sort(estados);
            for (Estado estado : estados) {
                serie.set(estado.getSigla(), mapEstado.get(estado).size());
            }

            graficoPorEstadoESexo.addSeries(serie);
        }
    }
}
