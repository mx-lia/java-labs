package by.belstu.maximchikova.bank.util;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static void throwValidationError(String summary, String detail) {
        FacesMessage message =
                new FacesMessage(summary, detail);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
    }
}
