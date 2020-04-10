package com.bs.domain.types;

/**
 * 
 * @author J. Milton Chambi M.
 */
public enum EstadoEnum {

    ENABLED("Activo", (byte)1), 
    DISABLED("Inactivo", (byte)2), 
    DELETED("Eliminado", (byte)3), 
    LOCKED("Bloqueado", (byte)4),
    EXPIRADO("Expirado", (byte) 5);

    private final String label;
    private final byte id;

    EstadoEnum(String label, byte id) {
        this.label = label;
        this.id = id;
    }

    public byte getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static EstadoEnum valueOf(byte id) {
        if (id > 0) {
            for (EstadoEnum item : values()) {
                if (item.id == id) {
                    return item;
                }
            }
        }
        throw new IllegalArgumentException("Estado enviado no valido : [" + id + "]");
    }

    public static boolean exist(byte id) {
        if (id > 0) {
            for (EstadoEnum item : values()) {
                if (item.id == id)
                    return true;
            }
        }
        return false;
    }

    public static String getString() {
        String concat = "[";
        for (EstadoEnum statusEnum : values()) {
            concat += "{ \"id\": " + statusEnum.getId() + ", \"label\": \"" + statusEnum.getLabel() + "\" }, ";
        }
        return concat.substring(0, concat.length() - 2) + "]";
    }


}
