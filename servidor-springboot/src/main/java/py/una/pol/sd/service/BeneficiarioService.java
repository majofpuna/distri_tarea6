package py.una.pol.sd.service;
import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Service; import py.una.pol.sd.repository.BeneficiarioRepository;
@Service public class BeneficiarioService { @Autowired BeneficiarioRepository repo; public void eliminar(Integer id){ repo.deleteById(id); } }