package ru.zhenya.rest.RestApi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zhenya.rest.RestApi.Dto.ChangesDto;
import ru.zhenya.rest.RestApi.Dto.ChangesResponse;
import ru.zhenya.rest.RestApi.models.Changes;
import ru.zhenya.rest.RestApi.service.ChangesService;
import ru.zhenya.rest.RestApi.util.ChangesErrorResponse;
import ru.zhenya.rest.RestApi.util.ChangesException;
import ru.zhenya.rest.RestApi.util.ChangesValidator;

import java.util.stream.Collectors;

import static ru.zhenya.rest.RestApi.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/weather")

public class DataController {

        private final ChangesService changesService;
        private final ChangesValidator changesValidator;
        private final ModelMapper modelMapper;


        public DataController(ChangesService changesService, ChangesValidator changesValidator, ModelMapper modelMapper) {
                this.changesService = changesService;
                this.changesValidator = changesValidator;
                this.modelMapper = modelMapper;
        }
        @PostMapping("/add")
        public ResponseEntity<HttpStatus> add(@RequestBody @Valid ChangesDto changesDto ,
                                              BindingResult bindingResult){
                Changes changesToAdd = convertToChanges(changesDto);

                changesValidator.validate(changesToAdd,bindingResult);
                if(bindingResult.hasErrors())
                        returnErrorsToClient(bindingResult);

                changesService.addChanges(changesToAdd);
                return ResponseEntity.ok(HttpStatus.OK);

        }
        @GetMapping("/rainyDaysCount")
        public Long getRainyDaysCount(){
                return changesService.findAll().stream().filter(Changes::getRaining).count();
        }
        @GetMapping()
        public ChangesResponse getChanges(){
                return new ChangesResponse(changesService.findAll().stream().map(this::convertToChangesDto).collect(Collectors.toList()));
        }


        public Changes convertToChanges (ChangesDto changesDto){
                return modelMapper.map(changesDto, Changes.class);
        }

        private ChangesDto convertToChangesDto(Changes changes){
                return modelMapper.map(changes, ChangesDto.class);
        }

        @ExceptionHandler
        private ResponseEntity<ChangesErrorResponse> handleException(ChangesException e){
                ChangesErrorResponse response = new ChangesErrorResponse(
                        e.getMessage(),
                        System.currentTimeMillis()
                );
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
}
