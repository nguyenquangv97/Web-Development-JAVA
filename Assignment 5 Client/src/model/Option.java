package model;

import java.io.*;

public class Option implements Serializable {
    private String name;
    private float price;

    // getters
    protected String getName() {
        return this.name;
    }

    protected float getPrice() {
        return this.price;
    }

    // setters
    protected void setName(String name) {
        this.name = name;
    }

    protected void setPrice(float price) {
        this.price = price;
    }

    protected Option() {
    }

    protected Option(String name, float price) {
        this.name = name;
        this.price = price;
    }

    protected void setProperties(String name, float price) {
        this.name = name;
        this.price = price;
    }

    protected String printOption() {
        StringBuffer sbuff = new StringBuffer();
        sbuff.append('\n');
        sbuff.append(this.name);
        sbuff.append(": ");
        sbuff.append(this.price);
        sbuff.append('\n');
        return sbuff.toString();
    }
}