package com.andesfresh.portal.config;
 
import com.andesfresh.portal.model.Empleado;
import com.andesfresh.portal.service.EmpleadoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
 
import java.time.LocalDate;
 
@Component
public class CargaInicial implements CommandLineRunner {
 
    private final EmpleadoService empleadoService;
 
    public CargaInicial(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }
 
    @Override
    public void run(String... args) {
        if (empleadoService.contar() == 0) {
            empleadoService.guardar(new Empleado("Rosa", "Quispe Huamán",
                    LocalDate.of(2023, 3, 1), 2800.0, "Av. Los Frutales 120, Ate"));
            empleadoService.guardar(new Empleado("Luis", "Mendoza Ríos",
                    LocalDate.of(2024, 7, 15), 2300.0, "Jr. Huancavelica 455, Lima"));
            empleadoService.guardar(new Empleado("Carla", "Torres Vega",
                    LocalDate.of(2025, 1, 10), 3100.0, "Av. La Molina 890, La Molina"));
        }
    }
}