package com.example.api_ciclovidaprod_security.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    public final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    public Producto buscarXid(int id) {
        return productoRepository.getById(id);
    }

    public List<Producto> getProductosContaining(String buscado) {
        return productoRepository.findByNombreContaining(buscado);
    }

    public void deleteById(int id) {
        productoRepository.deleteById(id);
    }

    public void agregarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public void modificarProducto(Producto producto) {
        productoRepository.save(producto);
    }
}
