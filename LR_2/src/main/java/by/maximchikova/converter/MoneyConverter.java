package by.maximchikova.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@FacesConverter("dollarConverter")
public class MoneyConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        BigDecimal moneyInDollars = BigDecimal.valueOf((Double)value / 2);
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.##");
        return decimalFormat.format(moneyInDollars);
    }
}
