package com.andesfresh.portal.repository;
 
// Proyección: cada fila del reporte agrupado por categoría
public interface ResumenCategoria {
    String getCategoria();
    Long getTotal();
    Double getValor();
}