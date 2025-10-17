package py.una.pol.sd.model;
import javax.persistence.*;
@Entity @Table(name = "clientes")
public class Cliente {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
  private String nombre; private String direccion; private String telefono;
  public Integer getId(){return id;} public String getNombre(){return nombre;}
  public String getDireccion(){return direccion;} public String getTelefono(){return telefono;}
  public void setId(Integer id){this.id=id;} public void setNombre(String n){this.nombre=n;}
  public void setDireccion(String d){this.direccion=d;} public void setTelefono(String t){this.telefono=t;}
}