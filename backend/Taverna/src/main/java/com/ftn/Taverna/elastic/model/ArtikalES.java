package com.ftn.Taverna.elastic.model;

import com.ftn.Taverna.model.Artikal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "artikli")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class ArtikalES {

    @Id
    private String id;

    private String naziv;

    @Field(type = FieldType.Double)
    private Double cena;

    @Field(type = FieldType.Text)
    private String opis;

    private Integer jpaId;



    //mapper
    public ArtikalES(Artikal artikal){
        this.jpaId = artikal.getId();
        this.naziv = artikal.getNaziv();
        this.cena = artikal.getCena();
        this.opis = artikal.getOpis();
    }




}
