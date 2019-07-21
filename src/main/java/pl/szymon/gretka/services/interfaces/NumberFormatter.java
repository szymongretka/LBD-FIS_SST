package pl.szymon.gretka.services.interfaces;

import java.text.NumberFormat;
import java.util.Locale;

public interface NumberFormatter {
	public String formatNumber(Double value, Locale locale);
}
