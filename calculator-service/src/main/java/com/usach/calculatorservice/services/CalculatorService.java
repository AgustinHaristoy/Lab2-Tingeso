package com.usach.calculatorservice.services;

import com.usach.calculatorservice.entities.CalculatorEntity;
import com.usach.calculatorservice.models.AcopioModel;
import com.usach.calculatorservice.models.GrasaSolidoModel;
import com.usach.calculatorservice.models.ProveedorModel;
import com.usach.calculatorservice.models.QuincenaModel;
import com.usach.calculatorservice.repositories.CalculatorRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CalculatorService {
    @Autowired
    private CalculatorRepository calculatorRepository;

    @Autowired
    RestTemplate restTemplate;

    private final Logger logg = LoggerFactory.getLogger(CalculatorService.class);

    public void guardarCalculos(CalculatorEntity calculos){
        calculatorRepository.save(calculos);
    }

    public ArrayList<CalculatorEntity> getAllcalculator(){return (ArrayList<CalculatorEntity>) calculatorRepository.findAll();}

    public List<AcopioModel> getAllacopio() {
        ResponseEntity<List<AcopioModel>> response = restTemplate.exchange(
                "http://acopio-service/acopio",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AcopioModel>>() {}
        );
        List<AcopioModel> acopio = response.getBody();
        return acopio;
    }
    
    public List<GrasaSolidoModel> getAllgrasasolido(){
        ResponseEntity<List<GrasaSolidoModel>> response = restTemplate.exchange(
                "http://grasasolido-service/grasasolido",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GrasaSolidoModel>>() {}
        );
        List<GrasaSolidoModel> grasasolido = response.getBody();
        return grasasolido;
    }

    public void guardarAcopio(String proveedor, String fecha, String turno, Double kls_leche){
        String url = "http://registro-service/registro/acopio";
        MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
        request.add("proveedor", proveedor);
        request.add("fecha", fecha);
        request.add("turno", turno);
        request.add("kls_leche", kls_leche);
        restTemplate.postForObject(url, request, Void.class);
    }

    public void guardarGrasaSolido(String proveedor, Double grasa, Double solido){
        String url = "http://registro-service/registro/grasasolido";
        MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
        request.add("proveedor", proveedor);
        request.add("grasa", grasa);
        request.add("solido", solido);
        restTemplate.postForObject(url, request, Void.class);
    }

    public void guardarUltimoRegistro(){
        List<AcopioModel> acopio = getAllacopio();
        List<GrasaSolidoModel> grasasolido = getAllgrasasolido();
        for(AcopioModel acopioModel : acopio) {
            guardarAcopio(acopioModel.getProveedor(), acopioModel.getFecha(), acopioModel.getTurno(), acopioModel.getKls_leche());
        }
        for(GrasaSolidoModel grasaSolidoModel : grasasolido) {
            guardarGrasaSolido(grasaSolidoModel.getProveedor(), grasaSolidoModel.getGrasa(), grasaSolidoModel.getSolido());
        }
    }

    public void crearQuincenasByProveedor(String proveedor){
        restTemplate.postForObject("http://quincena-service/quincena/quincenas/"+ proveedor, null, Void.class);
    }

    public QuincenaModel encontrarActual(String proveedor){
        QuincenaModel actual = restTemplate.getForObject("http://quincena-service/quincena/actual/"+ proveedor, QuincenaModel.class);
        return actual;
    }

    public QuincenaModel encontrarAnterior(String proveedor){
        QuincenaModel anterior = restTemplate.getForObject("http://quincena-service/quincena/anterior/"+ proveedor, QuincenaModel.class);
        return anterior;
    }

    public void generarPagos(String provedor){
        guardarUltimoRegistro();
        crearQuincenasByProveedor(provedor);
        QuincenaModel actual = encontrarActual(provedor);
        QuincenaModel anterior = encontrarAnterior(provedor);
        hacerCalculos(actual, anterior);
    }

    public ProveedorModel getProveedorByCodigo(String codigo){
        ProveedorModel proveedor = restTemplate.getForObject("http://proveedor-service/proveedores/"+ codigo, ProveedorModel.class);
        return proveedor;
    }
    @Generated
    public void hacerCalculos(QuincenaModel actual, QuincenaModel anterior){
        ProveedorModel proveedor = getProveedorByCodigo(actual.getProveedor());
        CalculatorEntity calculos = new CalculatorEntity();
        if(anterior != null) {
            calculos.setQuincena(obtenerQuincena(actual.getFecha(), actual.getQuincena()));
            calculos.setPromedio_kls_leche(calcularPromedio(actual.getKls_leche(), actual.getDias()));
            Double variacion_leche = calcularVariacion_leche(actual.getKls_leche(), anterior.getKls_leche());
            calculos.setVariacion_leche(variacion_leche);
            Double variacion_grasa = calcularVariacion_grasa(actual.getGrasa(), anterior.getGrasa());
            calculos.setVariacion_grasa(variacion_grasa);
            Double variacion_st = calcularVariacion_solido(actual.getSolido(), anterior.getSolido());
            calculos.setVariacion_st(variacion_st);
            double pago_leche = calcularPago_leche(actual.getKls_leche(), proveedor.getCategoria());
            calculos.setPago_leche(pago_leche);
            double pago_grasa = calcularPago_Grasa(actual.getGrasa(), actual.getKls_leche());
            calculos.setPago_grasa(pago_grasa);
            double pago_st = calcularPago_Solido(actual.getSolido(), actual.getKls_leche());
            calculos.setPago_st(pago_st);
            Double bonificacion_frecuencia = obtenerBonificacion_frecuencia(pago_leche, pago_grasa, pago_st, actual.getBonificacion());
            calculos.setBonificacion_frecuencia(bonificacion_frecuencia);
            Double pagoacopio = obtenerPagoAcopio(pago_leche, pago_grasa, pago_st, bonificacion_frecuencia);
            Double desc_leche = obtenerDescuento_leche(variacion_leche, pagoacopio);
            calculos.setDescuento_leche(desc_leche);
            Double desc_grasa = obtenerDescuento_grasa(variacion_grasa, pagoacopio);
            calculos.setDescuento_grasa(desc_grasa);
            Double desc_st = obtenerDescuento_solido(variacion_st, pagoacopio);
            calculos.setDescuento_st(desc_st);
            Double pago_total = obtenerPagoTotal(pagoacopio, desc_leche, desc_grasa, desc_st);
            calculos.setPago_total(pago_total);
            Double monto_retencion = obtenerMontoRetencion(pago_total, proveedor);
            calculos.setMonto_retencion(monto_retencion);
            Double monto_total = obtenerMontoTotal(pago_total, monto_retencion);
            calculos.setMonto_final(monto_total);
            calculos.setId(anterior.getId());
            guardarCalculos(calculos);
        }else{
            calculos.setQuincena(obtenerQuincena(actual.getFecha(), actual.getQuincena()));
            calculos.setPromedio_kls_leche(calcularPromedio(actual.getKls_leche(), actual.getDias()));
            double variacion = 0.0;
            calculos.setVariacion_leche(variacion);
            calculos.setVariacion_grasa(variacion);
            calculos.setVariacion_st(variacion);
            double pago_leche = calcularPago_leche(actual.getKls_leche(), proveedor.getCategoria());
            calculos.setPago_leche(pago_leche);
            double pago_grasa = calcularPago_Grasa(actual.getGrasa(), actual.getKls_leche());
            calculos.setPago_grasa(pago_grasa);
            double pago_st = calcularPago_Solido(actual.getSolido(), actual.getKls_leche());
            calculos.setPago_st(pago_st);
            Double bonificacion_frecuencia = obtenerBonificacion_frecuencia(pago_leche, pago_grasa, pago_st, actual.getBonificacion());
            calculos.setBonificacion_frecuencia(bonificacion_frecuencia);
            Double pagoacopio = obtenerPagoAcopio(pago_leche, pago_grasa, pago_st, bonificacion_frecuencia);
            Double desc_leche = obtenerDescuento_leche(variacion, pagoacopio);
            calculos.setDescuento_leche(desc_leche);
            Double desc_grasa = obtenerDescuento_grasa(variacion, pagoacopio);
            calculos.setDescuento_grasa(desc_grasa);
            Double desc_st = obtenerDescuento_solido(variacion, pagoacopio);
            calculos.setDescuento_st(desc_st);
            Double pago_total = obtenerPagoTotal(pagoacopio, desc_leche, desc_grasa, desc_st);
            calculos.setPago_total(pago_total);
            Double monto_retencion = obtenerMontoRetencion(pago_total, proveedor);
            calculos.setMonto_retencion(monto_retencion);
            Double monto_total = obtenerMontoTotal(pago_total, monto_retencion);
            calculos.setMonto_final(monto_total);
            calculos.setId(actual.getId());
            guardarCalculos(calculos);
        }
    }

    public String obtenerQuincena(String fecha,Integer quincena){
        String quincena1 = quincena.toString();
        String anio = fecha.substring(0,4);
        logg.info("anio: " + anio);
        String mes = fecha.substring(5,7);
        logg.info("mes: " + mes);
        String fecha1 = anio +"/"+ mes +"/"+ quincena1;
        logg.info("fecha: " + fecha1);
        return fecha1;
    }

    public Double calcularPromedio(double kls_leche, Integer dias){
        double promedio;
        double dias1 = Double.valueOf(dias);
        promedio = kls_leche / dias1;
        return promedio;
    }

    public Double calcularVariacion_leche(double kls_leche_actual, double kls_leche_anterior){
        double variacion;
        variacion = ((kls_leche_actual - kls_leche_anterior) / kls_leche_anterior) * 100;
        return variacion;
    }

    public Double calcularVariacion_grasa(Double grasa_actual, Double grasa_anterior){
        double variacion;
        variacion = ((grasa_actual - grasa_anterior) / grasa_anterior) * 100;
        return variacion;
    }

    public Double calcularVariacion_solido(Double solido_actual, Double solido_anterior){
        double variacion;
        variacion = ((solido_actual - solido_anterior) / solido_anterior) * 100;
        return variacion;
    }

    public double calcularPago_leche(double kls_leche, String categoria) {
        switch (categoria) {
            case "A" -> {
                return (kls_leche * 700);
            }
            case "B" -> {
                return (kls_leche * 550);
            }
            case "C" -> {
                return (kls_leche * 400);
            }
            case "D" -> {
                return (kls_leche * 250);
            }
        }
        return 0;
    }

    public double calcularPago_Grasa(Double grasa, double kls_leche){
        if(grasa >= 0 && grasa <= 20) {
            return (kls_leche * 30);
        }else if (grasa >= 21 && grasa <= 45) {
            return (kls_leche * 80);
        }else{
            return (kls_leche * 120);
        }
    }

    public double calcularPago_Solido(Double solido, double kls_leche){
        if(solido >= 0 && solido <= 7) {
            return (kls_leche * -130);
        }else if (solido >= 8 && solido <= 18) {
            return (kls_leche * -90);
        }else if (solido >= 19 && solido <= 35) {
            return (kls_leche * 95);
        }else{
            return (kls_leche * 150);
        }
    }

    public Double obtenerBonificacion_frecuencia(double pagoleche, double pagograsa, double pagosolido, Integer bonificacion){
        double bonificacionfrecuencia;
        if(bonificacion == 1) bonificacionfrecuencia = (pagoleche + pagograsa + pagosolido) * 0.12;
        else if(bonificacion == 2) bonificacionfrecuencia = (pagoleche + pagograsa + pagosolido) * 0.08;
        else if(bonificacion == 3) bonificacionfrecuencia = (pagoleche + pagograsa + pagosolido) * 0.2;
        else bonificacionfrecuencia = 0;
        return bonificacionfrecuencia;
    }

    public Double obtenerPagoAcopio(double pagoleche, double pagograsa, double pagosolido, Double bonificacionfrecuencia){
        double pagototal;
        pagototal = pagoleche + pagograsa + pagosolido + bonificacionfrecuencia;
        return pagototal;
    }

    public Double obtenerDescuento_leche(Double variacion_leche, Double pagoleche){
        if(variacion_leche <= 0.0 && variacion_leche >= -8.0) return pagoleche * 0.0;
        else if(variacion_leche <= -9.0 && variacion_leche >= -25.0) return pagoleche * 0.07;
        else if(variacion_leche <= -26.0 && variacion_leche >= -45.0) return pagoleche * 0.15;
        else return pagoleche * 0.30;
    }

    public Double obtenerDescuento_grasa(Double variacion_grasa, Double pagoleche){
        if(variacion_grasa <= 0.0 && variacion_grasa >= -15.0) return pagoleche * 0.0;
        else if(variacion_grasa <= -16.0 && variacion_grasa >= -25.0) return pagoleche * 0.12;
        else if(variacion_grasa <= -26.0 && variacion_grasa >= -40.0) return pagoleche * 0.2;
        else return pagoleche * 0.3;
    }

    public Double obtenerDescuento_solido(Double variacion_solido, Double pagoleche){
        if(variacion_solido <= 0.0 && variacion_solido >= -6.0) return pagoleche * 0.0;
        else if(variacion_solido <= -7.0 && variacion_solido >= -12.0) return pagoleche * 0.18;
        else if(variacion_solido <= -13.0 && variacion_solido >= -35.0) return pagoleche * 0.27;
        else return pagoleche * 0.45;
    }

    public Double obtenerPagoTotal(Double pagoacopio, Double descuento_leche, Double descuento_grasa, Double descuento_solido){
        double pagototal;
        pagototal = pagoacopio - descuento_leche - descuento_grasa - descuento_solido;
        return pagototal;
    }

    public Double obtenerMontoRetencion(Double pagototal, ProveedorModel proveedor){
        if(pagototal > 950000.0){
            proveedor.setRetencion(true);
            return pagototal * 0.13;
        }
        return 0.0;
    }

    public Double obtenerMontoTotal(Double pagototal, Double montoretencion){
        return pagototal - montoretencion;
    }
}
