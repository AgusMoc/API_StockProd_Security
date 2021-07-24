package com.example.api_ciclovidaprod_security.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    public final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping()
    public List<Producto> listarProductos(){
        return productoService.getProductos();
    }
    @GetMapping("id/{id}")
    public Producto buscarXid(@PathVariable int id){
        return productoService.buscarXid(id);
    }
    @GetMapping("encontrar/{buscado}")
    public List<Producto> listarProductosContaining(@PathVariable String buscado){
        return productoService.getProductosContaining(buscado);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id){
        productoService.deleteById(id);
    }
    @PostMapping("crear")
    public void agregarProducto(@RequestBody Producto producto){
        productoService.agregarProducto(producto);
    }
    @PutMapping("modificar")
    public void modificarProd(@RequestBody Producto producto){
        productoService.modificarProducto(producto);
    }
}
