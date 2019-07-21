package pl.szymon.gretka.services;

import java.text.NumberFormat;
import java.util.Locale;

import pl.szymon.gretka.services.interfaces.NumberFormatter;

public class NumberFormatterImpl implements NumberFormatter {

	@Override
	public String formatNumber(Double value, Locale locale) {
		return (locale != null) ? NumberFormat.getNumberInstance(locale).format(value) : NumberFormat.getNumberInstance().format(value);
	}

}
