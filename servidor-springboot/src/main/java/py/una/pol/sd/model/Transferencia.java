package py.una.pol.sd.model;
import javax.persistence.*; import java.time.LocalDateTime; import java.math.BigDecimal;
@Entity @Table(name = "transferencias")
public class Transferencia {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
  @Column(name="origen_cuenta") private String origenCuenta;
  @Column(name="destino_cuenta") private String destinoCuenta;
  private BigDecimal monto; private LocalDateTime fecha;
  public Integer getId(){return id;} public String getOrigenCuenta(){return origenCuenta;}
  public String getDestinoCuenta(){return destinoCuenta;} public BigDecimal getMonto(){return monto;}
  public LocalDateTime getFecha(){return fecha;} public void setId(Integer id){this.id=id;}
  public void setOrigenCuenta(String s){this.origenCuenta=s;} public void setDestinoCuenta(String s){this.destinoCuenta=s;}
  public void setMonto(BigDecimal m){this.monto=m;} public void setFecha(LocalDateTime f){this.fecha=f;}
}