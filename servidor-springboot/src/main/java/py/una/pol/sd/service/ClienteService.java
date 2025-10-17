package py.una.pol.sd.service;
import java.util.Optional; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Service;
import py.una.pol.sd.model.Cliente; import py.una.pol.sd.repository.ClienteRepository;
@Service public class ClienteService {
  @Autowired ClienteRepository repo;
  public Optional<Cliente> obtenerPorId(Integer id){ return repo.findById(id); }
  public Cliente actualizar(Integer id, Cliente d){ d.setId(id); return repo.save(d); }
}