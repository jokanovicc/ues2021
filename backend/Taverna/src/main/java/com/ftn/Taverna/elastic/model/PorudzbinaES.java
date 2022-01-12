package com.ftn.Taverna.elastic.model;

import com.ftn.Taverna.model.Porudzbina;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "porudzbine")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class PorudzbinaES {

    @Id
    private String id;

    private Integer jpaId;

    @Field(type = FieldType.Text)
    private String komentar;

    @Field(type = FieldType.Date, format = DateFormat.date)
    private Date satnica;

    @Field(type = FieldType.Boolean)
    private boolean anonimanKomentar;

    @Field(type = FieldType.Integer)
    private Integer ocena;

    @Field(type = FieldType.Double)
    private Double cena;


    public PorudzbinaES(Porudzbina porudzbina){
        this.jpaId = porudzbina.getId();
        this.komentar = porudzbina.getKomentar();
        this.satnica = porudzbina.getSatnica();
        this.anonimanKomentar = porudzbina.isAnonimanKomentar();
        this.ocena = porudzbina.getOcena();
    }


}
