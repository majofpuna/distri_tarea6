package py.una.pol.sd.service;
import java.util.List; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Service;
import py.una.pol.sd.model.Sucursal; import py.una.pol.sd.repository.SucursalRepository;
@Service public class SucursalService { @Autowired SucursalRepository repo; public List<Sucursal> listar(){ return repo.findAll(); } }