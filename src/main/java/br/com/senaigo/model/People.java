package br.com.senaigo.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
public class People {

    private String birthYear;
    private String eyeColor;
    private String gender;
    private String hairColor;
    private String height;
    private String homeWorld;
    private String mass;
    private String name;
    private String skinColor;
    private String created;
    private String edited;
    private String url;
    private Films f = new Films();
    List films = new ArrayList<Films>();
    List species = new ArrayList<Species>();
    List starships = new ArrayList<Starships>();
    List vehicles = new ArrayList<Vehicles> ();
}
