package com.usach.quincenaservice.services;

import com.usach.quincenaservice.entities.QuincenaEntity;
import com.usach.quincenaservice.models.RegistroModel;
import com.usach.quincenaservice.repositories.QuincenaRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuincenaService {
    @Autowired
    QuincenaRepository quincenaRepository;

    @Autowired
    RestTemplate restTemplate;

    private final Logger logg = LoggerFactory.getLogger(QuincenaService.class);

    public List<QuincenaEntity> getAllQuincenas(){return quincenaRepository.findAll();}

    public List<QuincenaEntity> getQuincenasByProveedor(String proveedor){
        return quincenaRepository.findByProveedor(proveedor);
    }
    @Generated
    public void actualizarAnterior(List<QuincenaEntity> quincenas){
        if(quincenas.isEmpty()){
            return;
        }
        for(QuincenaEntity quincena : quincenas){
            if (quincena.isActual()){
                quincena.setActual(false);
                quincena.setAnterior(true);
            }
            else if(quincena.isAnterior()){
                quincena.setAnterior(false);
            }
            quincenaRepository.save(quincena);
        }
    }

    public double getKlsTotales(String proveedor, String fecha){
        Double kls = restTemplate.getForObject("http://registro-service/registro/kls_totales/"+proveedor+"/"+fecha, Double.class);
        return kls;
    }

    public double getGrasaByProveedor(String proveedor, String fecha){
        Double grasa = restTemplate.getForObject("http://registro-service/registro/grasa/"+proveedor+"/"+fecha, Double.class);
        return grasa;
    }

    public double getSolidoByProveedor(String proveedor, String fecha){
        Double solido = restTemplate.getForObject("http://registro-service/registro/solido/"+proveedor+"/"+fecha, Double.class);
        return solido;
    }

    public int getDiasTotales(String proveedor, String fecha){
        int dias = restTemplate.getForObject("http://registro-service/registro/dias/"+proveedor+"/"+fecha, Integer.class);
        return dias;
    }

    public int getBonificacion(String proveedor, String fecha){
        int bonificacion = restTemplate.getForObject("http://registro-service/registro/bonificacion/"+proveedor+"/"+fecha, Integer.class);
        return bonificacion;
    }

    public Integer findQuincenaByFecha(String fecha){
        String fecha1 = fecha.substring(8,10);
        int fecha2 = Integer.parseInt(fecha1);
        if(fecha2 < 16) {
            return 1;
        }
        else{
            return 2;
        }
    }

    public List<RegistroModel> getAllregistro(){
        List<RegistroModel> registros = restTemplate.getForObject("http://registro-service/registro", List.class);
        return registros;
    }

    public void crearQuincena(String fecha, String proveedor){
        List<QuincenaEntity> Quincenas = getAllQuincenas();
        logg.info("Total Quincenas " + Quincenas.size());
        QuincenaEntity quincena = new QuincenaEntity();
        quincena.setFecha(fecha);
        quincena.setProveedor(proveedor);
        quincena.setKls_leche(getKlsTotales(proveedor, fecha));
        quincena.setActual(true);
        quincena.setAnterior(false);
        actualizarAnterior(Quincenas);
        quincena.setQuincena(findQuincenaByFecha(fecha));
        quincena.setGrasa(getGrasaByProveedor(proveedor, fecha));
        quincena.setSolido(getSolidoByProveedor(proveedor, fecha));
        quincena.setDias(getDiasTotales(proveedor, fecha));
        quincena.setBonificacion(getBonificacion(proveedor, fecha));
        quincenaRepository.save(quincena);
    }

    public void crearQuincenasByProveedor(String proveedor) {
        List<RegistroModel> registros = getAllregistro();
        logg.info("Total Registros" + registros.size());
        List<RegistroModel> registroEntityList = new ArrayList<>();
        String fecha;
        for(RegistroModel registro : registros){
            if(registro.getProveedor().equals(proveedor)){
                registroEntityList.add(registro);
            }
        }
        logg.info("Total Registros del proveedor" + registroEntityList.size());
        while(registroEntityList.size() != 0){
            fecha = registroEntityList.get(0).getFecha();
            crearQuincena(fecha, proveedor);
            List<RegistroEntity> registroQuincena = registroService.getRegistroByQuincena(fecha, registroEntityList);
            logg.info("Registros de la quincena " + registroQuincena.size());
            for(RegistroEntity registro : registroQuincena){
                registroEntityList.remove(registro);
                logg.info("Registros restantes " + registroEntityList.size());
            }
        }


    }

    public QuincenaEntity encontrarActual(String proveedor){
        List<QuincenaEntity> quincenas = getQuincenasByProveedor(proveedor);
        for(QuincenaEntity quincena : quincenas){
            if(quincena.isActual()){
                return quincena;
            }
        }
        return null;
    }

    public QuincenaEntity encontrarAnterior(String proveedor){
        List<QuincenaEntity> quincenas = getQuincenasByProveedor(proveedor);
        for(QuincenaEntity quincena : quincenas){
            if(quincena.isAnterior()){
                return quincena;
            }
        }
        return null;
    }

    public void eliminarQuincena(){
        List<QuincenaEntity> quincenas = getAllQuincenas();
        quincenaRepository.deleteAll(quincenas);
    }


}
