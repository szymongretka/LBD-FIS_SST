package pl.szymon.gretka.services.producers;

import java.text.NumberFormat;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import pl.szymon.gretka.services.NumberFormatterImpl;
import pl.szymon.gretka.services.interfaces.NumberFormatter;
import pl.szymon.gretka.services.qualifiers.Number;

@RequestScoped
public class NumberFormatterProducer {
	
	@Produces
    @Number
    public NumberFormatter getNumberFormatter() {
		return new NumberFormatterImpl();
    }
}
