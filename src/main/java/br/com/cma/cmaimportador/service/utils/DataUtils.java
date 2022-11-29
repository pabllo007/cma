package br.com.cma.cmaimportador.service.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {

    public static String getHoraMinutoSegundo() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
        String dataFormatada = sdf.format(hora);
        return dataFormatada;
    }
}
