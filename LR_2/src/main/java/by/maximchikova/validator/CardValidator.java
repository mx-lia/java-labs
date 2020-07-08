package by.maximchikova.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String value = o.toString();
        pattern = Pattern.compile("(?=[-0-9xX]{13}$)");
        matcher = pattern.matcher(value);
        if(!matcher.find()){
            String message = MessageFormat.format("{0} Incorrect format id", value);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "--", "--");
            throw new ValidatorException(facesMessage);
        }
    }
}
