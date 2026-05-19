package vista;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Usuario;

public class InicioSesion {

    private ArrayList<Usuario> usuarios;
    private Scanner teclado;

    public InicioSesion() {

        usuarios = new ArrayList<>();
        teclado = new Scanner(System.in);

        usuarios.add(new Usuario("admin", "admin123", true));
        usuarios.add(new Usuario("jose", "1234", false));
    }

    public Usuario iniciarSesion() {

        System.out.println("===== INICIO DE SESIÓN =====");

        System.out.print("Usuario: ");
        String nombre = teclado.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = teclado.nextLine();

        for (Usuario usuario : usuarios) {

            if (usuario.getNombre().equals(nombre)
                    && usuario.getContraseña().equals(contraseña)) {

                System.out.println("Inicio de sesión correcto.");

                return usuario;
            }
        }

        System.out.println("Usuario o contraseña incorrectos.");

        return null;
    }
}