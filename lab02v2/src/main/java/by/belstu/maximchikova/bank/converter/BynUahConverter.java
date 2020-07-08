package by.belstu.maximchikova.bank.converter;

import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.math.BigDecimal;

@Component
public class BynUahConverter implements Converter {
    private static final double BYN_TO_UAH_EXCHANGE_RATE = 12.08;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (!s.isEmpty()) {
            double byn = Double.parseDouble(s);
            return BigDecimal.valueOf(byn * BYN_TO_UAH_EXCHANGE_RATE);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
