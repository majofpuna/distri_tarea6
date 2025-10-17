package py.una.pol.sd.model;
import javax.persistence.*; import java.time.LocalDate; import java.math.BigDecimal;
@Entity @Table(name = "transacciones")
public class Transaccion {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
  @Column(name="cliente_id") private Integer clienteId;
  private LocalDate fecha; private String descripcion; private BigDecimal monto;
  public Integer getId(){return id;} public Integer getClienteId(){return clienteId;}
  public LocalDate getFecha(){return fecha;} public String getDescripcion(){return descripcion;}
  public BigDecimal getMonto(){return monto;} public void setId(Integer id){this.id=id;}
  public void setClienteId(Integer c){this.clienteId=c;} public void setFecha(LocalDate f){this.fecha=f;}
  public void setDescripcion(String d){this.descripcion=d;} public void setMonto(BigDecimal m){this.monto=m;}
}