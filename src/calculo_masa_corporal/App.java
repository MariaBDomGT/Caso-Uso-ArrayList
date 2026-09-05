package calculo_masa_corporal;
import Modulo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    
    private static ArrayList<Persona> listaPersonas = new ArrayList<>();//constante que almacena el registro de las personas con una lista de arreglos
    private static Scanner entrada = new Scanner(System.in);//sirve para leer y procesar la entrada de datos
    
    public static void main(String[] args) {
        int op = 0;//variable que servira para interactuar con el menu
        
        do{//comenzando el menu
            //mostrando las opciones que ofrece el menu
            System.out.println("~~~~~ SISTEMA DE BIENESTAR UNIVERSITARIO - EVALUACION IMC ~~~~~");
            System.out.println("1. Registrar persona");
            System.out.println("2. Mostrar personas registradas");
            System.out.println("3. Buscar personas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            
            try {//captura la entrada del usuario
                op = Integer.parseInt(entrada.nextLine());//convierte una cadena en un entero
            }
            catch (NumberFormatException e){//si el formato numerico es incorrecto
                System.out.println("Por favor, ingrese un numero valido");//mensaje de error
                continue;//continua con la peticion del usuario
            }
            
            switch (op){//funcion para acceder a las distintas partes del menu
                case 1://si el usuario selecciona 1
                    registrarPersona();//registra una nueva persona en el sistema
                    System.out.println("");//salto de linea
                    break;
                case 2://si el usuario selecciona 2
                    mostrarPersonas();//muestra todas las personas registradas hasta la fecha
                    System.out.println("");
                    break;
                case 3://si el usuario selecciona 3
                    buscarPersonas();//busca a una persona registrada, mediante su nombre completo
                    System.out.println("");
                    break;
                case 4://si el usuario selecciona 4
                    System.out.println("Saliendo del sistema...");//sale del sistema 
                    System.out.println("");
                    break;
                default://si el usuario selecciona una opcion diferente
                    System.out.println("Opcion invalida, por favor, intente de nuevo");//mensaje de error
            }
        }while(op != 4);//mientras la opcion sea diferente de 4, el menu se reiniciará 
    }
    
    public static void registrarPersona(){//funcion para registrar una nueva persona
        Persona p = new Persona();//creando un nuevo objeto Persona
        System.out.println("----- REGISTRAR PERSONA -----");
        
        //Ingresando los datos de la persona
        System.out.print("Ingrese el nombre completo: ");
        p.setNombreP(entrada.nextLine());//guardando el nombre de la persona en el atributo nombreP de la Clase Persona
        
        try {//capturando el año, mes y dia de nacimiento de la persona
            System.out.print("Ingrese el año de nacimiento (Ej. 2000): ");
            int anio = Integer.parseInt(entrada.nextLine());//guardando el año de nacimiento de la persona
            System.out.print("Ingrese el mes de nacimiento (Ej. 1 - 12): ");
            int mes = Integer.parseInt(entrada.nextLine());//guardando el mes de nacimiento de la persona
            System.out.print("Ingrese el día de nacimiento (Ej. 1 - 31): ");
            int dia = Integer.parseInt(entrada.nextLine());//guardando el dia de nacimiento de la persona
            
            p.setFechaNacP(LocalDate.of(anio,mes,dia));//guardando el año, mes y dia de la persona en el atributo FechaNacP de la Clase Persona
        } catch(Exception e){//si el año, mes o día son incorrectos
            System.out.println("Error al ingresar la fecha. Registro cancelado");//mensaje de error
            return;
        }
        
        //Validacion de la edad
        if(!p.esMayorEdad()){//validando si la persona es mayor de edad
            System.out.println("ERROR: La persona registrada es menor de edad (" + p.calcularEdad() + " años). Solo se permite registrar a personas mayores de edad (18 años)");
            return;
        }
        
        try {//capturando el peso y altura de la persona
            System.out.print("Ingrese el peso de la persona (en kg): ");
            p.setPesoP(Double.parseDouble(entrada.nextLine()));//guardando el peso de la persona y convirtiendo una cadena en un decimal
            
            System.out.print("Ingrese la altura de la persona (en mts): ");
            p.setAlturaP(Double.parseDouble(entrada.nextLine()));
            
        } catch(NumberFormatException e){//si el formato numerico es incorrecto
            System.out.println("ERROR: formato numerico incorrecto del peso/altura. Registro denegado");//mensaje de error
            return;
        }
        
        System.out.print("Ingrese la direccion de la persona: ");
        p.setDireccionP(entrada.nextLine());
        
        System.out.print("Ingrese el telefono de la persona: ");
        p.setTelefonoP(entrada.nextLine());
        
        System.out.print("Ingrese el correo de la persona: ");
        p.setCorreoP(entrada.nextLine());
        
        listaPersonas.add(p);//agregando personas a la lista
        System.out.println("Persona registrada con exito");//mensaje de confirmacion
    }
    
    public static void mostrarPersonas(){//funcion para mostrar la lista de personas registradas
        System.out.println("----- LISTA DE PERSONAS REGISTRADAS -----");
        
        if(listaPersonas.isEmpty()){//si la lista de personas esta vacia
            System.out.println("No hay personas registradas en el sistema");//mensaje
        }
        else {//si no esta vacia
            for(Persona p : listaPersonas){//recorriendo todos los elementos de la lista 
                p.mostrarDatosPersona();//mostrando los datos de todas las personas
                System.out.println("--------------------");
            }
        }
    }
    
    public static void buscarPersonas(){//funcion para buscar a la persona por su nombre
        System.out.println("----- BUSCAR PERSONA -----");
        //ingresando y guardando el dato
        System.out.print("Ingrese el nombre a buscar: ");
        String nombreBuscar = entrada.nextLine();
        boolean encontrado = false;//variable para corroborar el nombre
        
        for(Persona p : listaPersonas){//recorriendo todos los elementos de la lista
            if(p.getNombreP().equalsIgnoreCase(nombreBuscar) || p.getNombreP().toLowerCase().contains(nombreBuscar.toLowerCase())){//busca a un persona comparando el nombre ingresado por el nombre que es guardado en el sistmea
                //equalsIgnoreCase(): comparada dos palabras sin importar mayusculas o minusculas
                //toLowerCase(): convierte las mayusculas en minusculas
                //contains: verifica si una palabra contiene el texto que se busca
               System.out.println("----- REGISTRO ENCONTRADO -----");
               p.mostrarDatosPersona();//mostrando los datos de la persona
               System.out.println("--------------------");
               encontrado = true;//corrobora que el nombre si existe
            }
        }
        
        if(!encontrado){//si no aparece el nombre 
            System.out.println("No se ha encontrado ningun registro relacionado");//mensaje de error
        }
    }
}