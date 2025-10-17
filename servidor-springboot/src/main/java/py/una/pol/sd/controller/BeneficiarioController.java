package py.una.pol.sd.controller;
import org.springframework.web.bind.annotation.*; import org.springframework.http.*; import org.springframework.beans.factory.annotation.Autowired;
import py.una.pol.sd.service.BeneficiarioService;
@RestController @RequestMapping("/beneficiarios")
public class BeneficiarioController {
  @Autowired BeneficiarioService service;
  @DeleteMapping("/{id}")
  public ResponseEntity<?> eliminar(@PathVariable Integer id, @RequestHeader(value="X-Token", required=false) String token){
    if(token==null || !token.equals("abc123")){ return new ResponseEntity<>("Token inválido o faltante", HttpStatus.UNAUTHORIZED); }
    try{ service.eliminar(id); return new ResponseEntity<>("Eliminado", HttpStatus.OK); }
    catch(Exception e){ return new ResponseEntity<>("Error al eliminar", HttpStatus.BAD_REQUEST); }
  }
}