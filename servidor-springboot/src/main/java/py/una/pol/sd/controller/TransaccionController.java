package py.una.pol.sd.controller;
import org.springframework.web.bind.annotation.*; import org.springframework.http.*; import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate; import java.util.List; import py.una.pol.sd.service.TransaccionService; import py.una.pol.sd.model.Transaccion;
@RestController @RequestMapping("/transacciones")
public class TransaccionController {
  @Autowired TransaccionService service;
  @GetMapping
  public ResponseEntity<?> porFecha(@RequestParam(name="fecha", required=true) String fechaStr){
    try{ LocalDate f = LocalDate.parse(fechaStr); List<Transaccion> lista = service.porFecha(f); return new ResponseEntity<>(lista, HttpStatus.OK); }
    catch(Exception e){ return new ResponseEntity<>("Fecha inválida, use YYYY-MM-DD", HttpStatus.BAD_REQUEST); }
  }
}