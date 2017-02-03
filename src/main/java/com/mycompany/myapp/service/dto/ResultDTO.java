package com.mycompany.myapp.service.dto;

/**
 * Created by Y3895917F on 03/02/2017.
 */
public class ResultDTO {
    private Integer result;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public ResultDTO(Integer result) {

        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultDTO resultDTO = (ResultDTO) o;

        return result != null ? result.equals(resultDTO.result) : resultDTO.result == null;

    }

    @Override
    public int hashCode() {
        return result != null ? result.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
            "result=" + result +
            '}';
    }
}
