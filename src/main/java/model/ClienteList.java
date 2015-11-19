package model;

import java.util.ArrayList;
import java.util.List;

public class ClienteList {

    private List<Cliente> list;

    public ClienteList(){
        list = new ArrayList();
    }

    public void add(Cliente p){
        list.add(p);
    }

    public void setList(ArrayList<Cliente> select) {
        this.list = select;
    }
}