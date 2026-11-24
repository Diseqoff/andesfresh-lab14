package com.andesfresh.portal.service;
 
import com.andesfresh.portal.model.Producto;
import com.andesfresh.portal.repository.ProductoRepository;
import com.andesfresh.portal.repository.ResumenCategoria;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class ProductoService {
 
    private final ProductoRepository repo;
 
    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }
 
    public List<Producto> listar() { return repo.findAll(); }
    public Optional<Producto> buscarPorId(Long id) { return repo.findById(id); }
    public boolean existe(Long id) { return repo.existsById(id); }
    public Producto guardar(Producto p) { return repo.save(p); }
    public void eliminar(Long id) { repo.deleteById(id); }
 
    // ---- Semana 14: consultas tipo reporte ----
    public List<String> marcas() { return repo.listarMarcas(); }
    public List<String> categorias() { return repo.listarCategorias(); }
 
    public List<Producto> buscar(String marca, String categoria) {
        // "Seleccione..." envía texto vacío: lo convertimos en null para no filtrar
        return repo.buscar(vacioANull(marca), vacioANull(categoria));
    }
 
    public List<ResumenCategoria> resumenPorCategoria() { return repo.resumenPorCategoria(); }
 
    public double valorTotalInventario() {
        return resumenPorCategoria().stream().mapToDouble(ResumenCategoria::getValor).sum();
    }
 
    private String vacioANull(String valor) {
        return (valor == null || valor.isBlank()) ? null : valor;
    }
}