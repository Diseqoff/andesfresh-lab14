# Andes Fresh - Laboratorio 14: Interoperabilidad

Portal interno de Distribuidora Andes Fresh S.A.C. (Spring Boot 3 + Thymeleaf + JPA + H2).
Lenguajes de Programación II (IN-305) - Universidad Científica del Sur, 2026-2.

Requisitos: Java 17 o superior y Maven.

## Ejecutar
    mvn spring-boot:run

Abrir http://localhost:8080/

## Actividades
1. Navbar con fragmentos Thymeleaf (`fragments/layout.html`)
2. CRUD de empleados con validaciones (`/empleados`)
3. Consultas tipo reporte: búsqueda por marca y categoría (`/consultas/productos`) y resumen por categoría (`/consultas/resumen`)
4. API REST en JSON y XML (`/api/productos`, `/api/productos/xml`)
5. Exportación a CSV, Excel y PDF (`/reportes/csv`, `/reportes/excel`, `/reportes/pdf`)

## Probar la API
    curl -s http://localhost:8080/api/productos
    curl -i http://localhost:8080/api/productos/99
    curl -i -X POST http://localhost:8080/api/productos -H "Content-Type: application/json" -d '{"nombre":"Maracuyá","marca":"Valle Norte","categoria":"Frutas","precio":5.40,"cantidad":100}'
    curl -i -X DELETE http://localhost:8080/api/productos/9

La base H2 es en memoria: al reiniciar vuelven los 8 productos y los 3 empleados iniciales.
