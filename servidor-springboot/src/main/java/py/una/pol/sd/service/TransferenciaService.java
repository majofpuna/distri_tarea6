package py.una.pol.sd.service;
import java.time.LocalDateTime; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Service;
import py.una.pol.sd.model.Transferencia; import py.una.pol.sd.repository.TransferenciaRepository;
@Service public class TransferenciaService { @Autowired TransferenciaRepository repo;
  public Transferencia crear(Transferencia t){ if(t.getFecha()==null){ t.setFecha(LocalDateTime.now()); } return repo.save(t); } }