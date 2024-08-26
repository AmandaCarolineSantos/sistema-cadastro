
package br.com.sistemacadastro.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class LocalDateJsonAdapter extends XmlAdapter<String, LocalDate> {

    private final static List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    @Override
    public LocalDate unmarshal(String dateString) throws Exception {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
            }
        }
        throw new DateTimeParseException("Unparseable date: " + dateString, dateString, 0);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return FORMATTERS.get(1).format(localDate);
    }
}
