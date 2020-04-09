package com.bs.domain.utils;

import java.io.StringReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author J. Milton Chambi M.
 */
public class StringUtil {

    private static final int DEFAULT_SCALE = 2;
    private static final String[] UNIDADES = {"", "un(a) ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ",
        "ocho ", "nueve "};
    private static final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ", "cincuenta ", "sesenta ",
        "setenta ", "ochenta ", "noventa "};
    private static final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ",
        "quinientos ", "seiscientos ", "setecientos ", "ochocientos ", "novecientos "};

    public static String onlyLastCharacters(String string, int i) {
        if (string != null && string.length() > i) {
            string = string.substring(0, i);
            return string;
        }
        return string;
    }

    public static boolean isEmpty(String string) {
        if (string == null) {
            return true;
        }
        return string.trim().length() == 0;
    }

    private StringUtil() {
    }

    public static String stripAccents(String sentence) {
        sentence = Normalizer.normalize(sentence, Normalizer.Form.NFD);
        sentence = sentence.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return sentence;
    }

    public static String toInitCap(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    public static String toCurrencyFormat(BigDecimal decimal) {
        BigDecimal bd = decimal.setScale(DEFAULT_SCALE, BigDecimal.ROUND_DOWN);

        DecimalFormat df = new DecimalFormat();

        df.setMaximumFractionDigits(DEFAULT_SCALE);

        df.setMinimumFractionDigits(DEFAULT_SCALE);

        df.setGroupingUsed(false);

        return df.format(bd);
    }

    public static String capitalize(String text) {
        String c = (text != null) ? text.trim() : "";
        String[] words = c.split(" ");
        String result = "";
        for (String w : words) {
            result += (w.length() > 1 ? w.substring(0, 1).toUpperCase(Locale.US) + w.substring(1, w.length()).toLowerCase(Locale.US) : w) + " ";
        }
        return result.trim();
    }

    public static String convert(Double numero, boolean mayusculas, boolean incluirDecimales) {
        String literal = "";
        String parte_decimal;
        // se divide el numero 0000000,00 -> entero y decimal
        String Num[] = String.valueOf(numero).split("\\.");

        // de da formato al numero decimal
        // parte_decimal = Num[1] + "/100 Bolivianos.";
        if (incluirDecimales) {
            parte_decimal = ((Num[1].length() == 1) ? Num[1] + "0" : Num[1]) + "/100";
        } else {
            parte_decimal = "";
        }

        // se convierte el numero a literal
        if (Integer.parseInt(Num[0]) == 0) {// si el valor es cero
            literal = "cero ";
        } else if (Integer.parseInt(Num[0]) > 999999) {// si es millon
            literal = getMillones(Num[0]);
        } else if (Integer.parseInt(Num[0]) > 999) {// si es miles
            literal = getMiles(Num[0]);
        } else if (Integer.parseInt(Num[0]) > 99) {// si es centena
            literal = getCentenas(Num[0]);
        } else if (Integer.parseInt(Num[0]) > 9) {// si es decena
            literal = getDecenas(Num[0]);
        } else {// sino unidades -> 9
            literal = getUnidades(Num[0]);
        }
        // devuelve el resultado en mayusculas o minusculas
        if (mayusculas) {
            return (literal + parte_decimal).toUpperCase();
        } else {
            return (literal + parte_decimal);
        }
    }

    /* funciones para convertir los numeros a literales */
    private static String getUnidades(String numero) {// 1 - 9
        // si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
        String num = numero.substring(numero.length() - 1);
        return UNIDADES[Integer.parseInt(num)];
    }

    private static String getDecenas(String num) {// 99
        int n = Integer.parseInt(num);
        if (n < 10) {// para casos como -> 01 - 09
            return getUnidades(num);
        } else if (n > 19) {// para 20...99
            String u = getUnidades(num);
            if (u.equals("")) { // para 20,30,40,50,60,70,80,90
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
            }
        } else {// numeros entre 11 y 19
            return DECENAS[n - 10];
        }
    }

    private static String getCentenas(String num) {// 999 o 099
        if (Integer.parseInt(num) > 99) {// es centena
            if (Integer.parseInt(num) == 100) {// caso especial
                return " cien ";
            } else {
                return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
            }
        } else {// por Ej. 099
            // se quita el 0 antes de convertir a decenas
            return getDecenas(Integer.parseInt(num) + "");
        }
    }

    private static String getMiles(String numero) {// 999 999
        // obtiene las centenas
        String c = numero.substring(numero.length() - 3);
        // obtiene los miles
        String m = numero.substring(0, numero.length() - 3);
        String n = "";
        // se comprueba que miles tenga valor entero
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);
            return n + "mil " + getCentenas(c);
        } else {
            return "" + getCentenas(c);
        }

    }

    private static String getMillones(String numero) { // 000 000 000
        // se obtiene los miles
        String miles = numero.substring(numero.length() - 6);
        // se obtiene los millones
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        if (millon.length() > 1) {
            n = getCentenas(millon) + "millones ";
        } else {
            n = getUnidades(millon) + "millon ";
        }
        return n + getMiles(miles);
    }

    public static void main(String[] args) throws Exception {
        final String xmlStr = "<employees>"
                + "   <employee id=\"101\">"
                + "        <name>Lokesh Gupta</name>"
                + "       <title>Author</title>"
                + "   </employee>"
                + "   <employee id=\"102\">"
                + "        <name>Brian Lara</name>"
                + "       <title>Cricketer</title>"
                + "   </employee>"
                + "</employees>";

        String xmlString = xmlStr;
        //Use method to convert XML string content to XML Document object
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            System.out.println(doc);
            //return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
