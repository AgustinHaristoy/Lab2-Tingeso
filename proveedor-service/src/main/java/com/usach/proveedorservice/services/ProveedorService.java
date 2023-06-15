package com.usach.proveedorservice.services;

import com.usach.proveedorservice.entities.ProveedorEntity;
import com.usach.proveedorservice.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public List<ProveedorEntity> getAllproveedores(){
        return proveedorRepository.findAll();
    }

    public boolean idAlreadyexists(String codigo){
        return proveedorRepository.existsById(codigo);
    }

    public void save(ProveedorEntity proveedor){ proveedorRepository.save(proveedor);
    }

    public ProveedorEntity getProveedorByCodigo(String codigo_proveedor){
        return proveedorRepository.findByCodigoProveedor(codigo_proveedor);
    }

    public void delete(String codigo_proveedor){
        proveedorRepository.deleteById(codigo_proveedor);
    }


}
