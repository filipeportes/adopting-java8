package org.gojava.java7.enums;

import lombok.Getter;

@Getter
public enum Estado {
    ACRE("Acre", "AC", Regiao.NORTE),
    ALAGOAS("Alagoas", "AL", Regiao.NORDESTE),
    AMAPA("Amapá", "AP", Regiao.NORTE),
    AMAZONAS("Amazonas", "AM", Regiao.NORTE),
    BAHIA("Bahia", "BA", Regiao.NORDESTE),
    CEARA("Ceará", "CE", Regiao.NORDESTE),
    DISTRITO_FEDERAL("Distrito Federal", "DF", Regiao.CENTROOESTE),
    ESPIRITO_SANTO("Espírito Santo", "ES", Regiao.SUDESTE),
    GOIAS("Goiás", "GO", Regiao.CENTROOESTE),
    MARANHAO("Maranhão", "MA", Regiao.NORDESTE),
    MATO_GROSSO("Mato Grosso", "MT", Regiao.CENTROOESTE),
    MATO_GROSSO_DO_SUL("Mato Grosso do Sul", "MS", Regiao.CENTROOESTE),
    MINAS_GERAIS("Minas Gerais", "MG", Regiao.SUDESTE),
    PARA("Pará", "PA", Regiao.NORTE),
    PARAIBA("Paraíba", "PB", Regiao.NORDESTE),
    PARANA("Paraná", "PR", Regiao.SUL),
    PERNAMBUCO("Pernambuco", "PE", Regiao.NORDESTE),
    PIAUI("Piauí", "PI", Regiao.NORDESTE),
    RIO_DE_JANEIRO("Rio de Janeiro", "RJ", Regiao.SUDESTE),
    RIO_GRANDE_DO_NORTE("Rio Grande do Norte", "RN", Regiao.NORDESTE),
    RIO_GRANDE_DO_SUL("Rio Grande do Sul", "RS", Regiao.SUL),
    RONDONIA("Rondônia", "RO", Regiao.NORTE),
    RORAIMA("Roraima", "RR", Regiao.NORTE),
    SANTA_CATARINA("Santa Catarina", "SC", Regiao.SUL),
    SAO_PAULO("São Paulo", "SP", Regiao.SUDESTE),
    SERGIPE("Sergipe", "SE", Regiao.NORDESTE),
    TOCANTINS("Tocantins", "TO", Regiao.NORTE);

    private final String nome;
    private final String sigla;
    private final Regiao regiao;

    private Estado(String nome, String sigla, Regiao regiao) {
        this.nome = nome;
        this.sigla = sigla;
        this.regiao = regiao;
    }
}
