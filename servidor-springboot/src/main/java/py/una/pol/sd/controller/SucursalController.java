package py.una.pol.sd.controller;
import org.springframework.web.bind.annotation.*; import org.springframework.http.*; import org.springframework.beans.factory.annotation.Autowired;
import java.util.List; import py.una.pol.sd.service.SucursalService; import py.una.pol.sd.model.Sucursal;
@RestController @RequestMapping("/sucursales")
public class SucursalController {
  @Autowired SucursalService service;
  @GetMapping
  public ResponseEntity<List<Sucursal>> listar(){ return new ResponseEntity<>(service.listar(), HttpStatus.OK); }
}