package org.example.services;


import org.example.entities.DnaValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E>{
        public List<E> findAll() throws Exception; //Trae una lista de las personas que estan en la base de datos
        public E findById(String id) throws Exception;
        public E save(E entity) throws Exception; // crea una nueva entidad
        public E update(String id, E entity) throws Exception;
        public boolean delete(String id) throws Exception;
    }


