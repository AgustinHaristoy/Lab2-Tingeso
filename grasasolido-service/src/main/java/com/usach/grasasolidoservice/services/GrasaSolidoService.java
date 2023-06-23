package com.usach.grasasolidoservice.services;


import com.usach.grasasolidoservice.entities.GrasaSolidoEntity;
import com.usach.grasasolidoservice.repositories.GrasaSolidoRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrasaSolidoService {
    @Autowired
    GrasaSolidoRepository grasaSolidoRepository;

    @Autowired
    RestTemplate restTemplate;

    private final Logger logg = LoggerFactory.getLogger(GrasaSolidoService.class);

    public ArrayList<GrasaSolidoEntity> getAllgrasasolido(){return (ArrayList<GrasaSolidoEntity>) grasaSolidoRepository.findAll();}

    @Generated
    public String guardar(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null){
            if(!file.isEmpty()){
                try{
                    byte [] bytes = file.getBytes();
                    Path path  = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                    return "¡Archivo guardado exitosamente!";
                }
                catch (IOException e){
                    logg.error("ERROR", e);
                    return "No se pudo guardar el archivo";
                }
            }
            return "No se pudo guardar el archivo";
        }
        else{
            return "No se pudo guardar el archivo";
        }
    }

    @Generated
    public void leerCsv(String direccion){
        String texto = "";
        BufferedReader bf = null;
        List<GrasaSolidoEntity> grasaSolidoEntityList = getAllgrasasolido();
        for(GrasaSolidoEntity grasaSolidoEntity: grasaSolidoEntityList) {
            String url = "http://registro-service/registro/grasasolido";
            MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
            request.add("proveedor", grasaSolidoEntity.getProveedor());
            request.add("grasa", grasaSolidoEntity.getGrasa());
            request.add("solido", grasaSolidoEntity.getSolido());
            restTemplate.postForObject(url, request, Void.class);
        }
        grasaSolidoRepository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            int count = 1;
            while((bfRead = bf.readLine()) != null){
                if (count == 1){
                    count = 0;
                }
                else{
                    guardarDataDB_gs(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2]);
                    temp = temp + "\n" + bfRead;
                }
            }
            texto = temp;
            System.out.println("Archivo leido exitosamente");
        }catch(Exception e){
            System.err.println("No se encontro el archivo");
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    logg.error("ERROR", e);
                }
            }
        }
    }

    public void guardarData(GrasaSolidoEntity data){
        grasaSolidoRepository.save(data);
    }

    public void guardarDataDB_gs(String proveedor, String grasa , String solido){
        GrasaSolidoEntity newData = new GrasaSolidoEntity();
        newData.setProveedor(proveedor);
        double grasan = Double.parseDouble(grasa);
        if(grasan < 0.0){
            grasan = 0.0;
        }
        newData.setGrasa(grasan);
        double solidon = Double.parseDouble(solido);
        if(solidon < 0.0){
            solidon = 0.0;
        }
        newData.setSolido(solidon);
        guardarData(newData);
    }

    public void eliminarData(ArrayList<GrasaSolidoEntity> datas){
        grasaSolidoRepository.deleteAll(datas);
    }

    public GrasaSolidoEntity getGrasaSolidoById(Long id){
        return grasaSolidoRepository.findByID(id);
    }

    public void delete(Long id){
        GrasaSolidoEntity grasaSolidoEntity = getGrasaSolidoById(id);
        grasaSolidoRepository.delete(grasaSolidoEntity);
    }

    public GrasaSolidoEntity findbyProveedor(String proveedor){
        return grasaSolidoRepository.findByProveedor(proveedor);
    }


}
