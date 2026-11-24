package com.andesfresh.portal.repository;
 
import com.andesfresh.portal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import java.util.List;
 
public interface ProductoRepository extends JpaRepository<Producto, Long> {
 
    // Valores para llenar los combos
    @Query("SELECT DISTINCT p.marca FROM Producto p ORDER BY p.marca")
    List<String> listarMarcas();
 
    @Query("SELECT DISTINCT p.categoria FROM Producto p ORDER BY p.categoria")
    List<String> listarCategorias();
 
    // Búsqueda con filtros opcionales: si un parámetro es null, no filtra
    @Query("SELECT p FROM Producto p " +
           "WHERE (:marca IS NULL OR p.marca = :marca) " +
           "AND (:categoria IS NULL OR p.categoria = :categoria) " +
           "ORDER BY p.nombre")
    List<Producto> buscar(@Param("marca") String marca, @Param("categoria") String categoria);
 
    // Consulta tipo reporte: agrupa y totaliza
    @Query("SELECT p.categoria AS categoria, COUNT(p) AS total, " +
           "SUM(p.precio * p.cantidad) AS valor " +
           "FROM Producto p GROUP BY p.categoria ORDER BY p.categoria")
    List<ResumenCategoria> resumenPorCategoria();
}