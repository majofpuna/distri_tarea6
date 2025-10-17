package py.una.pol.sd.model;
import javax.persistence.*;
@Entity @Table(name = "beneficiarios")
public class Beneficiario {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
  private String nombre; private String cuenta;
  public Integer getId(){return id;} public String getNombre(){return nombre;} public String getCuenta(){return cuenta;}
  public void setId(Integer id){this.id=id;} public void setNombre(String n){this.nombre=n;} public void setCuenta(String c){this.cuenta=c;}
}