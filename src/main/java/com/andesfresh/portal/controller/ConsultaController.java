package com.andesfresh.portal.controller;
 
import com.andesfresh.portal.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
@RequestMapping("/consultas")
public class ConsultaController {
 
    private final ProductoService productoService;
 
    public ConsultaController(ProductoService productoService) {
        this.productoService = productoService;
    }
 
    @GetMapping("/productos")
    public String buscarProductos(@RequestParam(required = false) String marca,
                                  @RequestParam(required = false) String categoria,
                                  Model model) {
        model.addAttribute("marcas", productoService.marcas());
        model.addAttribute("categorias", productoService.categorias());
        model.addAttribute("marcaSel", marca);
        model.addAttribute("categoriaSel", categoria);
        model.addAttribute("productos", productoService.buscar(marca, categoria));
        return "consultas/productos";
    }
 
    @GetMapping("/resumen")
    public String resumen(Model model) {
        model.addAttribute("filas", productoService.resumenPorCategoria());
        model.addAttribute("total", productoService.valorTotalInventario());
        return "consultas/resumen";
    }
}