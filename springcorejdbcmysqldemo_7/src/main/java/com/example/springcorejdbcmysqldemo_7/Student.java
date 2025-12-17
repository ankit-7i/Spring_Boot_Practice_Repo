package com.example.springcorejdbcmysqldemo_7;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Student {

    private Integer sid;
    private String sname;
    private String saddress;
    private double marks;
    
}
