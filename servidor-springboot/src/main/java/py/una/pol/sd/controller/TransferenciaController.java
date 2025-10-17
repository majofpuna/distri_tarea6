package py.una.pol.sd.controller;
import org.springframework.web.bind.annotation.*; import org.springframework.http.*; import org.springframework.beans.factory.annotation.Autowired;
import py.una.pol.sd.service.TransferenciaService; import py.una.pol.sd.model.Transferencia;
@RestController @RequestMapping("/transferencias")
public class TransferenciaController {
  @Autowired TransferenciaService service;
  @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> crear(@RequestHeader(value="X-Token", required=false) String token, @RequestBody Transferencia t){
    if(token==null || !token.equals("abc123")){ return new ResponseEntity<>("Token inválido o faltante", HttpStatus.UNAUTHORIZED); }
    if(t.getOrigenCuenta()==null || t.getDestinoCuenta()==null || t.getMonto()==null){
      return new ResponseEntity<>("Campos requeridos: origen_cuenta, destino_cuenta, monto", HttpStatus.BAD_REQUEST);
    }
    if (t.getMonto().signum() <= 0) {
      return new ResponseEntity<>("El monto debe ser > 0", HttpStatus.BAD_REQUEST);
    }
    try{ Transferencia r = service.crear(t); return new ResponseEntity<>(r, HttpStatus.CREATED); }
    catch(Exception e){ return new ResponseEntity<>("Error al crear transferencia", HttpStatus.BAD_REQUEST); }
  }
}