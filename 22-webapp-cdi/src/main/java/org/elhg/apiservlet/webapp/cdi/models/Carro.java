package org.elhg.apiservlet.webapp.cdi.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.elhg.apiservlet.webapp.cdi.configs.CarroCompra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

//Inyecta el bean Carro en SessionScope, utilizado en ActualizarCarro y AgregarCarro
//@SessionScoped
//@Named
@CarroCompra
public class Carro implements Serializable {
    private List<ItemCarro> items;

    //Logger no es serializable no se puede guardar en la session
    //transient para que permita injectar el log, pero no forma parte de la session del carro
    //Se puede utilizar para imprimir datos en el log
    // private transient log =>Solo en los scope SessionScope ó ConversationScope
    // y que ademas implementen serializable
    @Inject
    private transient Logger log;

    @PostConstruct
    public void inicializar(){
        this.items = new ArrayList<>();
        log.info("inicializando el carro de compras!");
    }

    @PreDestroy
    public void destruir(){
        log.info("destruyendo el carro de compras!");
    }

    public void addItemCarro(ItemCarro itemCarro) {
        if (items.contains(itemCarro)) {
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro))
                    .findAny();
            if (optionalItemCarro.isPresent()) {
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1);
            }
        } else {
            this.items.add(itemCarro);
        }
    }
    public List<ItemCarro> getItems() {
        return items;
    }

    public int getTotal() {
        return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }

    public void removeProductos(List<String> productoIds) {
        if (productoIds != null) {
            productoIds.forEach(this::removeProducto);
            // que es lo mismo a:
            // productoIds.forEach(productoId -> removeProducto(productoId));
        }
    }

    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemCarro> findProducto(String productoId) {
        return  items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
}
