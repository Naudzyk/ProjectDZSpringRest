package ru.zhenya.rest.RestApi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zhenya.rest.RestApi.Dto.SensorDto;
import ru.zhenya.rest.RestApi.models.Sensor;
import ru.zhenya.rest.RestApi.service.SensorService;
import ru.zhenya.rest.RestApi.util.ChangesErrorResponse;
import ru.zhenya.rest.RestApi.util.ChangesException;
import ru.zhenya.rest.RestApi.util.SensorValidator;

import static ru.zhenya.rest.RestApi.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensor")
public class RegistrationController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;
    @Autowired
    public RegistrationController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;

    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDto sensorDto,
                                                   BindingResult bindingResult){

        Sensor sensorToAdd = convertToSensor(sensorDto);

        sensorValidator.validate(sensorToAdd, bindingResult);

        if(bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        sensorService.register(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<ChangesErrorResponse> handleException(ChangesException e){
        ChangesErrorResponse response = new ChangesErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);



    }

    private Sensor convertToSensor(SensorDto sensorDto){
        return modelMapper.map(sensorDto,Sensor.class);
    }
}
