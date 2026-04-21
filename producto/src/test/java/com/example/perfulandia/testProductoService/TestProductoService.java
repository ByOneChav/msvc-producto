package com.example.perfulandia.testProductoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.perfulandia.repository.ProductoRepository;
import com.example.perfulandia.service.ProductoService;
import com.example.perfulandia.model.Producto;



@ExtendWith(MockitoExtension.class)
public class TestProductoService {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodo(){
        List<Producto> listaProducto = new ArrayList<>();

        Producto p1 = new Producto();

        p1.setIdProducto(1L);
        p1.setNombreProducto("Esika");
        p1.setCategoria("perfumes");
        p1.setStock(9);
        p1.setPrecio(78990);
        p1.setFechaCaducidad(LocalDate.parse("2026-09-12"));

        listaProducto.add(p1);

        when(productoRepository.findAll()).thenReturn(listaProducto);
        List<Producto> resultadoBuscado = productoService.buscarTodo();
        assertEquals(1, resultadoBuscado.size());
        verify(productoRepository, times(1)).findAll();

    }

    @Test
    public void testBuscarUnProducto(){

        Producto p1 = new Producto();

        p1.setIdProducto(1L);
        p1.setNombreProducto("Esika");
        p1.setCategoria("perfumes");
        p1.setStock(9);
        p1.setPrecio(78990);
        p1.setFechaCaducidad(LocalDate.parse("2026-09-12"));

        when(productoRepository.findById(1L)).thenReturn(Optional.of(p1));
        Producto productoBuscado = productoService.buscar(1L);
        assertEquals(1, productoBuscado.getIdProducto());
        verify(productoRepository, times(1)).findById(1L);

    }

    @Test
    public void testGuardarUnProducto(){

        Producto p1 = new Producto();

        p1.setIdProducto(1L);
        p1.setNombreProducto("Esika");
        p1.setCategoria("perfumes");
        p1.setStock(9);
        p1.setPrecio(78990);
        p1.setFechaCaducidad(LocalDate.parse("2026-09-12"));

        when(productoRepository.save(p1)).thenReturn(p1);
        Producto productoGuardado = productoService.guardar(p1);
        assertEquals(1, productoGuardado.getIdProducto());
        verify(productoRepository, times(1)).save(p1);
    }

    @Test
    public void testEliminarProducto(){
        Long idProducto= 1L; 
        doNothing().when(productoRepository).deleteById(idProducto);

        productoService.eliminar(idProducto);
        verify(productoRepository, times(1)).deleteById(idProducto);
    }

}
