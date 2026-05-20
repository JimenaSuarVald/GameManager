package Modelo;

public class Combate {
    public enum EstadoCombate { ACTIVO, VICTORIA, DERROTA }

    private int turno;
    private int turnosTranscurridos;
    private Enemigos[] enemigos;
    private Jugador jugador;
    private EstadoCombate estado;
    private String[] mensajes;
    private int numMensajes;
    private int defensaExtra;
    private int turnosEvasion;
    private int turnosMultiplicador;

    public Combate(Enemigos[] enemigos, Jugador jugador) {
        this.turno = 0;
        this.turnosTranscurridos = 0;
        this.jugador = jugador;
        this.estado = EstadoCombate.ACTIVO;
        this.mensajes = new String[100];
        this.numMensajes = 0;
        this.defensaExtra = 0;
        this.turnosEvasion = 0;
        this.turnosMultiplicador = 0;
        jugador.setDañoVeneno(0);

        this.enemigos = new Enemigos[enemigos.length];
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null) {
                Enemigos o = enemigos[i];
                this.enemigos[i] = new Enemigos(o.getNombre(), o.getDaño(), o.getVida(), o.getTipoDaño());
            }
        }
    }

    public EstadoCombate getEstado() { return estado; }
    public Enemigos[] getEnemigos() { return enemigos; }
    public Jugador getJugador() { return jugador; }
    public int getTurnosTranscurridos() { return turnosTranscurridos; }

    public String[] getMensajes() {
        String[] copia = new String[numMensajes];
        for (int i = 0; i < numMensajes; i++) {
            copia[i] = mensajes[i];
        }
        return copia;
    }

    public void limpiarMensajes() {
        for (int i = 0; i < numMensajes; i++) {
            mensajes[i] = null;
        }
        numMensajes = 0;
    }

    private void agregarMensaje(String m) {
        if (numMensajes < mensajes.length) {
            mensajes[numMensajes++] = m;
        }
    }

    public boolean puedeUsarHabilidad(int indice) {
        return turnosTranscurridos > indice;
    }

    private int primerEnemigoVivo() {
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null && enemigos[i].getVida() > 0) return i;
        }
        return -1;
    }

    private void comprobarEstadoCombate() {
        if (jugador.getVida() <= 0) {
            estado = EstadoCombate.DERROTA;
            agregarMensaje("Has muerto... Fin de la partida.");
            return;
        }
        boolean todosMuertos = true;
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null && enemigos[i].getVida() > 0) {
                todosMuertos = false;
                break;
            }
        }
        if (todosMuertos) {
            estado = EstadoCombate.VICTORIA;
            agregarMensaje("¡Has ganado el combate!");
        }
    }

    public void defender() {
        if (estado == EstadoCombate.ACTIVO && turno == 0) {
            defensaExtra = 20;
            agregarMensaje("Te preparas para defender, reducirás 20 de daño este turno.");
            pasarTurno();
        }
    }

    public void usarHabilidad(Habilidades habilidad) {
        if (estado == EstadoCombate.ACTIVO && turno == 0) {
            String efecto = habilidad.getEfecto();
            if (efecto.equals("Cura")) {
                jugador.setVida(jugador.getVida() + habilidad.getMagnitud());
                agregarMensaje("Usas " + habilidad.getNombre() + " y te curas " + habilidad.getMagnitud() + " puntos.");
            } else if (efecto.equals("DañoTotal")) {
                for (int i = 0; i < enemigos.length; i++) {
                    if (enemigos[i] != null) {
                        enemigos[i].setVida(enemigos[i].getVida() - habilidad.getMagnitud());
                    }
                }
                agregarMensaje("Usas " + habilidad.getNombre() + " causando " + habilidad.getMagnitud() + " de daño a todos.");
            } else if (efecto.equals("Veneno")) {
                int idx = primerEnemigoVivo();
                if (idx != -1) {
                    int actual = enemigos[idx].getDañoVeneno();
                    enemigos[idx].setDañoVeneno(actual + habilidad.getMagnitud());
                    agregarMensaje("Envenenas a " + enemigos[idx].getNombre() + " (veneno total: " + (actual + habilidad.getMagnitud()) + "/turno).");
                }
            } else if (efecto.equals("Aturdimiento")) {
                int idx = primerEnemigoVivo();
                if (idx != -1) {
                    enemigos[idx].setTurnosAturdido(habilidad.getMagnitud());
                    agregarMensaje("Aturdes a " + enemigos[idx].getNombre() + " durante " + habilidad.getMagnitud() + " turnos.");
                }
            } else if (efecto.equals("Defensa")) {
                defensaExtra = habilidad.getMagnitud();
                agregarMensaje("Te proteges con " + habilidad.getNombre() + ", reduces " + habilidad.getMagnitud() + " de daño este turno.");
            } else if (efecto.equals("Evasión")) {
                turnosEvasion = habilidad.getMagnitud();
                agregarMensaje("Usas " + habilidad.getNombre() + ", esquivarás ataques durante " + habilidad.getMagnitud() + " turnos.");
            } else if (efecto.equals("MultiplicadorDaño")) {
                turnosMultiplicador = habilidad.getMagnitud();
                agregarMensaje("Usas " + habilidad.getNombre() + ", tu daño x2 durante " + habilidad.getMagnitud() + " ataques.");
            } else if (efecto.equals("Quemazón")) {
                for (int i = 0; i < enemigos.length; i++) {
                    if (enemigos[i] != null) {
                        enemigos[i].setVida(enemigos[i].getVida() - habilidad.getMagnitud());
                    }
                }
                agregarMensaje("Usas " + habilidad.getNombre() + " causando " + habilidad.getMagnitud() + " de daño ígneo a todos.");
            } else if (efecto.equals("Hipnosis")) {
                int idx = primerEnemigoVivo();
                if (idx != -1) {
                    enemigos[idx].setTurnosHipnotizado(habilidad.getMagnitud());
                    agregarMensaje("Hipnotizas a " + enemigos[idx].getNombre() + ". Atacará a sus compañeros durante " + habilidad.getMagnitud() + " turnos.");
                } else {
                    agregarMensaje("No hay enemigos a los que hipnotizar.");
                }
            } else {
                agregarMensaje("La habilidad " + habilidad.getNombre() + " no tiene efecto conocido.");
            }

            comprobarEstadoCombate();
            if (estado == EstadoCombate.ACTIVO) {
                pasarTurno();
            }
        }
    }

    public void procesarObjetos(Item seleccion) {
        if (estado == EstadoCombate.ACTIVO && turno == 0) {
            String efecto = seleccion.getEfecto();
            if (efecto.equals("Curar")) {
                jugador.setVida(jugador.getVida() + seleccion.getMagnitud());
                agregarMensaje("Te has curado " + seleccion.getMagnitud() + " puntos de vida.");
                pasarTurno();
            } else if (efecto.equals("Daño")) {
                int idx = primerEnemigoVivo();
                if (idx != -1) {
                    enemigos[idx].setVida(enemigos[idx].getVida() - seleccion.getMagnitud());
                    agregarMensaje("Haces " + seleccion.getMagnitud() + " de daño a " + enemigos[idx].getNombre() + ".");
                }
                comprobarEstadoCombate();
                if (estado == EstadoCombate.ACTIVO) pasarTurno();
            } else if (efecto.equals("Escapar")) {
                estado = EstadoCombate.VICTORIA;
                agregarMensaje("Huyes del combate.");
            } else if (efecto.equals("TurnoExtra")) {
                jugador.setTurnosExtra(jugador.getTurnosExtra() + seleccion.getMagnitud());
                agregarMensaje("Ganas " + seleccion.getMagnitud() + " turnos extra.");
                pasarTurno();
            } else if (efecto.equals("Ceguera")) {
                int idx = primerEnemigoVivo();
                if (idx != -1) {
                    enemigos[idx].setTurnosCegado(seleccion.getMagnitud());
                    agregarMensaje("Has cegado a " + enemigos[idx].getNombre() + " por " + seleccion.getMagnitud() + " turnos.");
                }
                pasarTurno();
            } else if (efecto.equals("EquipajeAtaque")) {
                jugador.setArma(seleccion);
                agregarMensaje("Te has equipado el arma: " + seleccion.getNombre());
                pasarTurno();
            }
        }
    }

    private void pasarTurno() {
        if (this.turno == 0) {
            if (jugador.getTurnosExtra() > 0) {
                jugador.gastarTurnoExtra();
                agregarMensaje("Aprovechas tu turno extra. Vuelves a actuar.");
            } else {
                this.turno = 1;
                ejecutarTurnoEnemigo();
            }
        }
    }

    public void atacar(int entrada) {
        if (estado == EstadoCombate.ACTIVO && turno == 0) {
            if (enemigos[entrada] != null) {
                Enemigos Atacado = enemigos[entrada];
                int daño = jugador.getDaño();
                if (turnosMultiplicador > 0) {
                    daño *= 2;
                    turnosMultiplicador--;
                }
                int vidaEnemigo = Atacado.getVida() - daño;
                Atacado.setVida(vidaEnemigo);
                agregarMensaje("Atacas a " + Atacado.getNombre() + " causando " + daño + " de daño.");

                if (vidaEnemigo <= 0) {
                    agregarMensaje(Atacado.getNombre() + " ha sido derribado.");
                    enemigos[entrada] = null;
                }

                comprobarEstadoCombate();
                if (estado == EstadoCombate.ACTIVO) {
                    pasarTurno();
                }
            }
        }
    }

    private int otroEnemigoVivo(int excepto) {
        for (int j = 0; j < enemigos.length; j++) {
            if (j != excepto && enemigos[j] != null && enemigos[j].getVida() > 0) return j;
        }
        return -1;
    }

    private void ejecutarTurnoEnemigo() {
        if (estado == EstadoCombate.ACTIVO && turno == 1) {
            if (jugador.getDañoVeneno() > 0) {
                jugador.setVida(jugador.getVida() - jugador.getDañoVeneno());
                agregarMensaje("Sufres " + jugador.getDañoVeneno() + " de daño por veneno.");
                comprobarEstadoCombate();
                if (estado != EstadoCombate.ACTIVO) return;
            }

            for (int i = 0; i < enemigos.length; i++) {
                if (enemigos[i] == null) continue;

                if (enemigos[i].getDañoVeneno() > 0) {
                    enemigos[i].setVida(enemigos[i].getVida() - enemigos[i].getDañoVeneno());
                    agregarMensaje(enemigos[i].getNombre() + " sufre " + enemigos[i].getDañoVeneno() + " de daño por veneno.");
                    if (enemigos[i].getVida() <= 0) {
                        agregarMensaje(enemigos[i].getNombre() + " ha caído en combate.");
                        enemigos[i] = null;
                        continue;
                    }
                }

                if (enemigos[i].getVida() <= 0) {
                    agregarMensaje(enemigos[i].getNombre() + " ha caído en combate.");
                    enemigos[i] = null;
                } else if (enemigos[i].getTurnosCegado() >= 1) {
                    enemigos[i].reducirCeguera();
                    agregarMensaje(enemigos[i].getNombre() + " está cegado y no hace nada.");
                } else if (enemigos[i].getTurnosHipnotizado() >= 1) {
                    enemigos[i].reducirHipnosis();
                    int objetivo = otroEnemigoVivo(i);
                    if (objetivo != -1) {
                        int daño = enemigos[i].getDaño();
                        enemigos[objetivo].setVida(enemigos[objetivo].getVida() - daño);
                        agregarMensaje(enemigos[i].getNombre() + " está hipnotizado y ataca a " + enemigos[objetivo].getNombre() + " causando " + daño + " de daño.");
                        if (enemigos[objetivo].getVida() <= 0) {
                            agregarMensaje(enemigos[objetivo].getNombre() + " cae traicionado.");
                            enemigos[objetivo] = null;
                        }
                    } else {
                        agregarMensaje(enemigos[i].getNombre() + " está hipnotizado pero no hay compañeros que atacar.");
                    }
                } else if (enemigos[i].getTurnosAturdido() >= 1) {
                    enemigos[i].reducirAturdimiento();
                    agregarMensaje(enemigos[i].getNombre() + " está aturdido y pierde su turno.");
                } else {
                    int dañoEnemigo = enemigos[i].getDaño();
                    if (turnosEvasion > 0) {
                        agregarMensaje("Esquivas el ataque de " + enemigos[i].getNombre() + ".");
                    } else {
                        int dañoFinal = dañoEnemigo - defensaExtra;
                        if (dañoFinal < 0) dañoFinal = 0;
                        jugador.setVida(jugador.getVida() - dañoFinal);
                        agregarMensaje(enemigos[i].getNombre() + " te hace " + dañoFinal + " puntos de daño.");

                        String tipo = enemigos[i].getTipoDaño();
                        if (tipo.equals("Veneno")) {
                            jugador.setDañoVeneno(jugador.getDañoVeneno() + 10);
                            agregarMensaje(enemigos[i].getNombre() + " te ha envenenado. Veneno total: " + jugador.getDañoVeneno() + "/turno.");
                        } else if (tipo.equals("VenenoPotente")) {
                            jugador.setDañoVeneno(jugador.getDañoVeneno() + 20);
                            agregarMensaje(enemigos[i].getNombre() + " te ha envenenado gravemente. Veneno total: " + jugador.getDañoVeneno() + "/turno.");
                        }
                    }
                }
            }

            defensaExtra = 0;
            if (turnosEvasion > 0) turnosEvasion--;
            turnosTranscurridos++;

            comprobarEstadoCombate();
            if (estado == EstadoCombate.ACTIVO) {
                this.turno = 0;
            }
        }
    }
}