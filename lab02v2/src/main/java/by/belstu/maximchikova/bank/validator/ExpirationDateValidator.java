package by.belstu.maximchikova.bank.validator;

import by.belstu.maximchikova.bank.util.ValidationUtils;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ExpirationDateValidator implements Validator {
    private static final String EXPIRATION_DATE_REGEX = "^\\d{2}\\/\\d{4}$";
    private static final Pattern EXPIRATION_DATE_PATTERN = Pattern.compile(EXPIRATION_DATE_REGEX);
    private static final String SUMMARY_TEXT = "Expiration date validation failed";

    @Override
    public void validate(FacesContext facesContext,
                         UIComponent component, Object value) {
        String expValue = value.toString();
        Matcher matcher = EXPIRATION_DATE_PATTERN.matcher(expValue);

        if (!matcher.matches()) {
            ValidationUtils.throwValidationError(SUMMARY_TEXT,
                    "Expiration date should match the dd/yyyy format");
        }

        String[] expParts = expValue.split("/");

        int month = Integer.parseInt(expParts[0]);
        if (month < 1 || month > 12) {
            ValidationUtils.throwValidationError(SUMMARY_TEXT,
                    "Month should be in the range from 1 to 12");
        }

        int year = Integer.parseInt(expParts[1]);

        LocalDate localDate = LocalDate.of(year, month, 1);
        if (localDate.isBefore(LocalDate.now())) {
            ValidationUtils.throwValidationError(SUMMARY_TEXT,
                    "Expiration date should be in the future");
        }
    }
}
