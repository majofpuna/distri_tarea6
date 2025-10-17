package py.una.pol.sd.model;
import javax.persistence.*;
@Entity @Table(name = "sucursales")
public class Sucursal {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
  private String nombre; private String ciudad;
  public Integer getId(){return id;} public String getNombre(){return nombre;} public String getCiudad(){return ciudad;}
  public void setId(Integer id){this.id=id;} public void setNombre(String n){this.nombre=n;} public void setCiudad(String c){this.ciudad=c;}
}