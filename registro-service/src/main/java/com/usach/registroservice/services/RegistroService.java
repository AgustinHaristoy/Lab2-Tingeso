package com.usach.registroservice.services;

import com.usach.registroservice.entities.RegistroEntity;
import com.usach.registroservice.repositories.RegistroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RegistroService {
    @Autowired
    private RegistroRepository registroRepository;

    private final Logger logg = LoggerFactory.getLogger(RegistroService.class);

    public List<RegistroEntity> getAllregistro(){return registroRepository.findAll();}

    /*public List<RegistroEntity> getRegistroByProveedor(String proveedor){
        return registroRepository.findByProveedor(proveedor);
    }*/
    public void guardarRegistro(RegistroEntity registro){ registroRepository.save(registro);}

    public void guardarAcopio(String proveedor, String fecha, String turno, Double kls_leche){
        List<RegistroEntity> registros = getAllregistro();
        if(registros != null) {
            for (RegistroEntity registro : registros) {
                if (registro.getProveedor().equals(proveedor) && registro.getFecha().equals(fecha) && registro.getTurno().equals(turno)) {
                    return;
                }
            }
        }
        RegistroEntity newRegistro = new RegistroEntity();
        newRegistro.setProveedor(proveedor);
        newRegistro.setFecha(fecha);
        newRegistro.setTurno(turno);
        newRegistro.setKls_leche(kls_leche);
        guardarRegistro(newRegistro);
    }

    public void guardarGrasaSolido(String proveedor, Double grasa, Double solido){
        List<RegistroEntity> registros = getAllregistro();
        for(RegistroEntity registro : registros){
            if(registro.getProveedor().equals(proveedor) && registro.getGrasa() == null && registro.getSolido() == null){
                registro.setGrasa(grasa);
                registro.setSolido(solido);
                guardarRegistro(registro);
            }
        }
    }

    public List<RegistroEntity> findByFecha_Month(String fecha){
        List<RegistroEntity> registros = getAllregistro();
        List<RegistroEntity> registrosMes = new ArrayList<>();
        for(RegistroEntity registro : registros){
            if(registro.getFecha().substring(0,4).equals(fecha.substring(0,4))
                    && registro.getFecha().substring(5,7).equals(fecha.substring(5,7))){
                registrosMes.add(registro);
            }
        }
        return registrosMes;
    }

    public List<RegistroEntity> getRegistroByQuincena(String fecha){
        List<RegistroEntity> registroEntityList = findByFecha_Month(fecha);
        List<RegistroEntity> registroEntityList1 = new ArrayList<>();
        Integer quincena = findQuincenaByFecha(fecha);
        Integer quincena1;
        for(RegistroEntity registroEntity: registroEntityList){
            quincena1 = findQuincenaByFecha(registroEntity.getFecha());
            if (quincena.equals(quincena1) && registroEntity.getFecha().substring(0,4).equals(fecha.substring(0,4)) && registroEntity.getFecha().substring(5,7).equals(fecha.substring(5,7))){
                registroEntityList1.add(registroEntity);
            }
        }
        return registroEntityList1;
    }


    public double getKlsTotales(String proveedor, String fecha){
        List<RegistroEntity> registroEntityList = getRegistroByQuincena(fecha);
        double kls = 0;
        for (RegistroEntity registroEntity: registroEntityList) {
            if (registroEntity.getProveedor().equals(proveedor)){
                kls = kls + registroEntity.getKls_leche();
            }
        }
        return kls;
    }

    public Integer getDiasTotales(String proveedor, String fecha){
        List<RegistroEntity> registroEntityList = getRegistroByQuincena(fecha);
        int dias = 0;
        boolean visto = false;
        String fecha1 = null;
        for (RegistroEntity registroEntity: registroEntityList) {
            if (registroEntity.getProveedor().equals(proveedor)){
                if (!visto){
                    fecha1 = registroEntity.getFecha();
                    dias = dias + 1;
                    visto = true;
                }
                else{
                    if (!Objects.equals(fecha1, registroEntity.getFecha())){
                        dias = dias + 1;
                        fecha1 = registroEntity.getFecha();
                    }else{
                        visto = false;
                    }
                }
            }
        }
        return dias;
    }
    public Integer getBonificacion(String proveedor, String fecha){
        List<RegistroEntity> registroEntityList = getRegistroByQuincena(fecha);
        int manana = 0;
        int tarde = 0;
        int mananaytarde = 0;
        int bonificacion = 0;
        boolean visto = false;
        String fecha1 = null;
        String turno = null;
        for (RegistroEntity registroEntity: registroEntityList) {
            if (registroEntity.getProveedor().equals(proveedor)){
                if (!visto){
                    if(fecha1 != null){
                        if(!Objects.equals(fecha1, registroEntity.getFecha())){
                            fecha1 = registroEntity.getFecha();
                            turno = registroEntity.getTurno();
                            visto = true;}
                    }
                    fecha1 = registroEntity.getFecha();
                    logg.info("fecha1: " + fecha1);
                    turno = registroEntity.getTurno();
                    logg.info("turno: " + turno);
                    visto = true;
                }
                else{
                    if (!Objects.equals(fecha1, registroEntity.getFecha())){
                        if(turno.equals("M")){
                            manana = manana + 1;
                            logg.info("manana: " + manana);
                        }else{
                            tarde = tarde + 1;
                            logg.info("tarde: " + tarde);
                        }
                        fecha1 = registroEntity.getFecha();
                        turno = registroEntity.getTurno();
                    }else{
                        if(!Objects.equals(turno, registroEntity.getTurno())){
                            mananaytarde = mananaytarde + 1;
                            visto = false;
                        }
                    }
                }
            }
        }
        if (Objects.equals(turno, "M")) {
            manana = manana + 1;
            logg.info("manana: " + manana);
        } else {
            tarde = tarde + 1;
        }
        if (mananaytarde > 10){
            bonificacion = 3;
        }else{
            if (manana > tarde && manana > 10) {
                bonificacion = 1;
            }else if (tarde > manana && tarde > 10) {
                bonificacion = 2;
            }
        }
        logg.info("bonificacion: " + bonificacion);
        return bonificacion;
    }

    public Double getGrasaByProveedor(String proveedor, String fecha){
        logg.info("fecha: " + fecha);
        List<RegistroEntity> registroEntityList = getRegistroByQuincena(fecha);
        Double grasa = 0.0;
        for (RegistroEntity registroEntity: registroEntityList) {
            if (registroEntity.getProveedor().equals(proveedor)){
                grasa = registroEntity.getGrasa();
                logg.info("grasa: " + grasa);
            }
        }
        return grasa;
    }

    public Double getSolidoByProveedor(String proveedor, String fecha){
        List<RegistroEntity> registroEntityList = getRegistroByQuincena(fecha);
        double solido = 0.0;
        for (RegistroEntity registroEntity: registroEntityList) {
            if (registroEntity.getProveedor().equals(proveedor)){
                solido = registroEntity.getSolido();
            }
        }
        return solido;
    }

    public void eliminarRegistro(){
        registroRepository.deleteAll();
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

}
