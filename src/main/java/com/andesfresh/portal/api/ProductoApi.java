package com.andesfresh.portal.api;
 
import com.andesfresh.portal.model.Producto;
import com.andesfresh.portal.service.ProductoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import java.net.URI;
import java.util.List;
 
@RestController
@RequestMapping("/api/productos")
public class ProductoApi {
 
    private final ProductoService productoService;
 
    public ProductoApi(ProductoService productoService) {
        this.productoService = productoService;
    }
 
    // GET /api/productos  -> lista en JSON
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Producto> listar() {
        return productoService.listar();
    }
 
    // GET /api/productos/xml  -> la misma lista en XML
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Producto> listarXml() {
        return productoService.listar();
    }
 
    // GET /api/productos/3  -> un producto (404 si no existe)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> buscar(@PathVariable Long id) {
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
 
    // POST /api/productos  -> crea (201 Created)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        producto.setId(null);
        Producto nuevo = productoService.guardar(producto);
        return ResponseEntity.created(URI.create("/api/productos/" + nuevo.getId())).body(nuevo);
    }
 
    // PUT /api/productos/3  -> actualiza
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        if (!productoService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        producto.setId(id);
        return ResponseEntity.ok(productoService.guardar(producto));
    }
 
    // DELETE /api/productos/3  -> elimina (204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!productoService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}