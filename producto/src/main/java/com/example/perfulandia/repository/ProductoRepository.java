package com.example.perfulandia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.perfulandia.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

    
} 