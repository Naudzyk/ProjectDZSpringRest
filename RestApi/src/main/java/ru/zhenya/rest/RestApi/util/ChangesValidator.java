package ru.zhenya.rest.RestApi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.zhenya.rest.RestApi.models.Changes;
import ru.zhenya.rest.RestApi.service.ChangesService;
import ru.zhenya.rest.RestApi.service.SensorService;

@Component
public class ChangesValidator implements Validator {
    private final SensorService sensorService;
    @Autowired

    public ChangesValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Changes.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Changes changes = (Changes) target;

        if(changes.getSensor() == null){
            return;
        }

        if(sensorService.findByName(changes.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor","Нет зарег сенсора");
        }

    }
}
