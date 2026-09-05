package Modulo;
import java.time.Period;
import java.time.LocalDate;

public class Persona {
    //Atributos
    private String nombreP;
    private LocalDate fechaNacP;
    private double pesoP;
    private double alturaP;
    private String direccionP;
    private String telefonoP;
    private String correoP;
    
    //Metodos
    
    //Constructor
    public Persona(){}//estara vacio
    
    //getter 
    public String getNombreP(){return nombreP;}
    public LocalDate getFechaNacP(){return fechaNacP;}
    public double getPesoP(){return pesoP;}
    public double getAlturaP(){return alturaP;}
    public String getDireccionP(){return direccionP;}
    public String getTelefonoP(){return telefonoP;}
    public String getCorreoP(){return correoP;}
    
    //setter
    public void setNombreP(String nomP){this.nombreP = nomP;}
    public void setFechaNacP(LocalDate fecNP){this.fechaNacP = fecNP;}
    public void setPesoP(double pesP){this.pesoP = pesP;}
    public void setAlturaP(double altP){this.alturaP = altP;}
    public void setDireccionP(String direP){this.direccionP = direP;}
    public void setTelefonoP(String telP){this.telefonoP = telP;}
    public void setCorreoP(String corrP){this.correoP = corrP;}
    
    //Logica del negocio
    public int calcularEdad(){//metodo para calcular la edad
        if(fechaNacP != null){//si la fecha de nacimiento de la persona no esta vacia
            return Period.between(fechaNacP, LocalDate.now()).getYears();
        }
        return 0;
    }
    
    public boolean esMayorEdad(){//metodo para comprobar si es mayor de edad
        return calcularEdad() >= 18;
    }
    
    public double calcularIMC(){//metodo que calcula el IMC
        if(alturaP > 0){
            return pesoP/(alturaP*alturaP);
        }
        return 0.0;
    }
    
    public String clasificarIMC(){//metodo para clasificar el peso segun la OMS
        double IMC = calcularIMC();
        if(IMC < 18.5){return "Peso bajo";}
        else if(IMC >= 18.5 && IMC <= 24.9){return "Peso normal";}
        else if(IMC >= 25.0 && IMC <= 29.9){return "Sobrepeso";}
        else{return "Obesidad";}
    }
    
    public void mostrarDatosPersona(){
        System.out.println("Nombre de la persona: " + nombreP);
        System.out.println("Fecha de nacimiento de la persona: " + fechaNacP);
        System.out.println("Edad de la persona: " + calcularEdad() + " años");
        System.out.println("Peso de la persona: " + pesoP);
        System.out.println("Altura de la persona: " + alturaP);
        System.out.println("Direccion de la persona: " + direccionP);
        System.out.println("Telefono de la persona: " + telefonoP);
        System.out.println("Correo de la persona: " + correoP);
        System.out.println("IMC de la persona: %.2f" + calcularIMC());//%.2f sirve para representar un numero con solo dos decimales
        System.out.println("Clasificacion del IMC de la persona: " + clasificarIMC());
    }
}