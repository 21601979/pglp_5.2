package fr.uvsq._2;

import java.time.LocalDate;

import fr.uvsq._2.Personnel.builder;

public class main {

    public static void main(String[] args){
       
       BDD.initBDD();
       builder b = new builder("bow","ser",LocalDate.of(1, 1, 1),1);
       b.setfonction("roi");
       PersonnelJdbcDAO test = new PersonnelJdbcDAO();
       test.find("1");
    }
}

