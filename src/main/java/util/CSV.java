package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSV {
	private static String archivo="registroCSV.csv";
	private CSV() {}
	public static void escribir(String nombre, int idGrupo, int idGeneral) {
	    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true))) {
	        escritor.write(String.join(",", nombre, String.valueOf(idGrupo), String.valueOf(idGeneral)));
	        escritor.newLine();
	        System.out.println("Registro agregado con éxito.");
	    } catch (IOException e) {
	        System.out.println("Error al escribir el archivo: " + e.getMessage());
	    }
	}
	public static void nuevo() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, false))){} 
		catch (IOException e) {
	        System.out.println("Error al borrar registro" + e.getMessage());
	    }
	}
	public static void leer() {
	    try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
	        String linea;
	        while ((linea = lector.readLine()) != null) {
	            String[] valores = linea.split(",");
	            for (String valor : valores) {
	                System.out.print(valor + " | ");
	            }
	            System.out.println();
	        }
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    }
	}
	private static int[] leerUltimoId(String nombre) {
	    int maxIdGrupo = Integer.MIN_VALUE;
	    int maxIdGeneral = Integer.MIN_VALUE;
	    try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
	        String linea;
	        while ((linea = lector.readLine()) != null) {
	            String[] valores = linea.split(",");
	            if (valores.length >= 3 && valores[0].equals(nombre)) {
	                int idGrupo = Integer.parseInt(valores[1]);
	                int idGeneral = Integer.parseInt(valores[2]);
	                if (idGrupo > maxIdGrupo) {
	                    maxIdGrupo = idGrupo;
	                }
	                if (idGeneral > maxIdGeneral) {
	                    maxIdGeneral = idGeneral;
	                }
	            }
	        }
	    } catch (IOException | NumberFormatException e) {
	        System.out.println("Error al leer o procesar el archivo: " + e.getMessage());
	    }
	    if (maxIdGrupo == Integer.MIN_VALUE || maxIdGeneral == Integer.MIN_VALUE) {
	        return new int[] {-1,-1}; // No se encontró el nombre
	    }
	    return new int[] {maxIdGrupo, maxIdGeneral};
	}
	
	public static int[] ultimoId(String nombre) {
		int ids[]=new int[2];
		switch(nombre) {
			case "Fuerza":
				ids=leerUltimoId("Fuerza");
				break;
			default:
				ids[0]=-1;
				ids[1]=-1;
		}
		return ids;
	}
}
