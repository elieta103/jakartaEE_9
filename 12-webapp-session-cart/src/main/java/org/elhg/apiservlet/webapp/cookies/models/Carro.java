package org.elhg.apiservlet.webapp.cookies.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    private List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<ItemCarro>();
    }

    public void addItem(ItemCarro itemCarro){
        if(items.contains(itemCarro)){
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro))
                    .findAny();
            if(optionalItemCarro.isPresent()){
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1);
            }

        }else {
            this.items.add(itemCarro);
        }
    }

    public int getTotal(){
        return items.stream().map(ItemCarro::getImporte).reduce(0, Integer::sum);
        //return items.stream().map(i-> i.getImporte()).reduce(0, (a,b)->a+b);
        //return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }

    public List<ItemCarro> getItems() {
        return items;
    }
}
