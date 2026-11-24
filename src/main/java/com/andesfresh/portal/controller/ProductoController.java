package com.andesfresh.portal.controller;
 
import com.andesfresh.portal.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class ProductoController {
 
    private final ProductoService productoService;
 
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
 
    @GetMapping("/productos")
    public String catalogo(Model model) {
        model.addAttribute("productos", productoService.listar());
        return "productos/lista";
    }
}
