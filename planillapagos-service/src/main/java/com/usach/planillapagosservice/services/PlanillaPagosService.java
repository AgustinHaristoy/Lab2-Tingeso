package com.usach.planillapagosservice.services;

import com.usach.planillapagosservice.entities.PlanillaPagosEntity;
import com.usach.planillapagosservice.models.CalculatorModel;
import com.usach.planillapagosservice.models.ProveedorModel;
import com.usach.planillapagosservice.models.QuincenaModel;
import com.usach.planillapagosservice.repositories.PlanillaPagosRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanillaPagosService {
    @Autowired
    private PlanillaPagosRepository planillaPagosRepository;

    @Autowired
    RestTemplate restTemplate;

    public void guardarPlanilla(PlanillaPagosEntity planillaPagosEntity){
        planillaPagosRepository.save(planillaPagosEntity);
    }

    public ArrayList<PlanillaPagosEntity> getAllPlanillaPagos(){
        return (ArrayList<PlanillaPagosEntity>) planillaPagosRepository.findAll();
    }

    public void generarPagos(String proveedor){
        restTemplate.postForObject("http://calculator-service/calculator/"+ proveedor, null, Void.class);
    }

    public List<CalculatorModel> getAllcalculator() {
        ResponseEntity<List<CalculatorModel>> response = restTemplate.exchange(
                "http://calculator-service/calculator",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CalculatorModel>>() {}
        );
        List<CalculatorModel> calculators = response.getBody();
        return calculators;
    }

    public QuincenaModel encontrarActual(String proveedor){
        QuincenaModel actual = restTemplate.getForObject("http://quincena-service/quincena/actual/"+ proveedor, QuincenaModel.class);
        return actual;
    }

    public ProveedorModel getProveedorByCodigo(String codigo){
        ProveedorModel proveedor = restTemplate.getForObject("http://proveedor-service/proveedores/"+ codigo, ProveedorModel.class);
        return proveedor;
    }
    @Generated
    public void crearPlanilla(String proveedor){
        PlanillaPagosEntity planillaPagosEntity = new PlanillaPagosEntity();
        ProveedorModel proveedorN = getProveedorByCodigo(proveedor);
        planillaPagosEntity.setProveedor(proveedor);
        planillaPagosEntity.setNombre(proveedorN.getNombre());
        generarPagos(proveedor);
        List<CalculatorModel> calculatorModels = getAllcalculator();
        CalculatorModel calculatorEntity = calculatorModels.get(calculatorModels.size()-1);
        QuincenaModel actual = encontrarActual(proveedor);
        planillaPagosEntity.setQuincena(calculatorEntity.getQuincena());
        planillaPagosEntity.setTotal_kls_leche(actual.getKls_leche());
        planillaPagosEntity.setDias(actual.getDias());
        planillaPagosEntity.setPromedio_kls_leche(calculatorEntity.getPromedio_kls_leche());
        planillaPagosEntity.setVariacion_leche(calculatorEntity.getVariacion_leche());
        planillaPagosEntity.setGrasa(actual.getGrasa());
        planillaPagosEntity.setVariacion_grasa(calculatorEntity.getVariacion_grasa());
        planillaPagosEntity.setSolidos_totales(actual.getSolido());
        planillaPagosEntity.setVariacion_st(calculatorEntity.getVariacion_st());
        planillaPagosEntity.setPago_leche(calculatorEntity.getPago_leche());
        planillaPagosEntity.setPago_grasa(calculatorEntity.getPago_grasa());
        planillaPagosEntity.setPago_st(calculatorEntity.getPago_st());
        planillaPagosEntity.setBonificacion_frecuencia(calculatorEntity.getBonificacion_frecuencia());
        planillaPagosEntity.setDescuento_leche(calculatorEntity.getDescuento_leche());
        planillaPagosEntity.setDescuento_grasa(calculatorEntity.getDescuento_grasa());
        planillaPagosEntity.setDescuento_st(calculatorEntity.getDescuento_st());
        planillaPagosEntity.setPago_total(calculatorEntity.getPago_total());
        planillaPagosEntity.setMonto_retencion(calculatorEntity.getMonto_retencion());
        planillaPagosEntity.setMonto_final(calculatorEntity.getMonto_final());
        guardarPlanilla(planillaPagosEntity);
    }
}
