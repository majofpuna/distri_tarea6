package py.una.pol.sd.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

import py.una.pol.sd.service.ClienteService;
import py.una.pol.sd.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
  @Autowired ClienteService service;
  @GetMapping("/{id}")
  public ResponseEntity<?> obtener(@PathVariable Integer id){
    Optional<Cliente> c = service.obtenerPorId(id);
    if(c.isPresent()) return new ResponseEntity<>(c.get(), HttpStatus.OK);
    return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
  }
  @PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestHeader(value="X-Token", required=false) String token, @RequestBody Cliente datos){
    if (token == null || !token.equals("abc123")) {
      return new ResponseEntity<>("Token inválido o faltante", HttpStatus.UNAUTHORIZED);
    }
    if (datos.getId() != null && !datos.getId().equals(id)) {
      return new ResponseEntity<>("ID del body no coincide con el path", HttpStatus.BAD_REQUEST);
    }
    try{ Cliente r = service.actualizar(id, datos); return new ResponseEntity<>(r, HttpStatus.OK); }
    catch(Exception e){ return new ResponseEntity<>("Error al actualizar", HttpStatus.BAD_REQUEST); }
  }
}