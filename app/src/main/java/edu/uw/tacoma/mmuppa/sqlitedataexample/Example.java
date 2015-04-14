package edu.uw.tacoma.mmuppa.sqlitedataexample;

import java.io.Serializable;

/**
 * Created by mmuppa on 4/13/15.
 */
public class Example implements Serializable {

    private int id;
    private String name;

    public Example(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Example{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
