package ru.zhenya.rest.RestApi.Dto;

import java.util.List;

public class ChangesResponse {
    private List<ChangesDto> changesD;

    public ChangesResponse(List<ChangesDto> changesD) {
        this.changesD = changesD;
    }

    public List<ChangesDto> getChangesD(){
        return changesD;
    }

    public void setChangesD(List<ChangesDto> changesD) {this.changesD = changesD;}
}
