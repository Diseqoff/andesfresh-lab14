package com.andesfresh.portal.controller;
 
import com.andesfresh.portal.model.Empleado;
import com.andesfresh.portal.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
 
    private final EmpleadoService empleadoService;
 
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }
 
    // Listado
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("empleados", empleadoService.listar());
        return "empleados/lista";
    }
 
    // Formulario vacío
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("titulo", "Registro de empleados");
        return "empleados/form";
    }
 
    // Formulario con datos para editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        return empleadoService.buscarPorId(id)
                .map(e -> {
                    model.addAttribute("empleado", e);
                    model.addAttribute("titulo", "Editar empleado");
                    return "empleados/form";
                })
                .orElseGet(() -> {
                    flash.addFlashAttribute("mensaje", "El empleado " + id + " no existe");
                    return "redirect:/empleados";
                });
    }
 
    // Guardar (nuevo o editado)
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("empleado") Empleado empleado,
                          BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", empleado.getId() == null ? "Registro de empleados" : "Editar empleado");
            return "empleados/form";
        }
        empleadoService.guardar(empleado);
        flash.addFlashAttribute("mensaje", "Empleado guardado correctamente");
        return "redirect:/empleados";
    }
 
    // Eliminar
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        empleadoService.eliminar(id);
        flash.addFlashAttribute("mensaje", "Empleado eliminado");
        return "redirect:/empleados";
    }
}