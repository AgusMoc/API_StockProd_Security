package com.example.api_ciclovidaprod_security.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("id/{id}")   //TODO revisar metodo
    public Producto buscarXid(@PathVariable int id){
        return productoService.buscarXid(id);
    }

    @GetMapping("encontrar/{buscado}")
    @PreAuthorize("hasAnyAuthority('producto:read','producto:write')")
    public List<Producto> listarProductosContaining(@PathVariable String buscado){
        return productoService.getProductosContaining(buscado);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable int id){
        productoService.deleteById(id);
    }

    @PostMapping("crear")
    @PreAuthorize("hasAuthority('producto:write')")
    public void agregarProducto(@RequestBody Producto producto){
        productoService.agregarProducto(producto);
    }
    @PutMapping("modificar")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public void modificarProd(@RequestBody Producto producto){
        productoService.modificarProducto(producto);
    }
}
