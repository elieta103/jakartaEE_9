package org.elhg.webapp.jsf3.converters;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.elhg.webapp.jsf3.entities.Categoria;
import org.elhg.webapp.jsf3.services.ProductoService;

import java.util.Optional;

@RequestScoped
@Named("categoriaConverter")
public class CategoriaConverter implements Converter<Categoria> {

    @Inject
    private ProductoService service;

    // CONVERTIR EL ID DE CATEGORIA (String id), A UN OBJETO CATEGORIA
    @Override
    public Categoria getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {  // Primer item del selected
            return null;
        }
        Optional<Categoria> categoriaOptional = service.porIdCategoria(Long.valueOf(id));
        if (categoriaOptional.isPresent()) {
            return categoriaOptional.get();
        }
        return null;
    }

    // CONVERTIR EL OBJETO CATEGORIA(Categoria) a un ID DE CATEGORIA (String id)
    @Override
    public String getAsString(FacesContext context, UIComponent component, Categoria categoria) {
        if (categoria == null) {
            return "0";
        }
        return categoria.getId().toString();
    }
}
