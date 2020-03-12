package br.com.senaigo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String data;
    protected String uuid;
    protected String userName;
    protected byte[] avatar;
}